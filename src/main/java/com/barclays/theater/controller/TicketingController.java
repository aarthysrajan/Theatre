package main.java.com.barclays.theater.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import main.java.com.barclays.theater.constants.TheaterConstants;
import main.java.com.barclays.theater.model.SeatRequest;
import main.java.com.barclays.theater.model.Row;
import main.java.com.barclays.theater.model.Section;
import main.java.com.barclays.theater.model.Theater;

public class TicketingController {
	
	private final static Logger logger = Logger.getLogger(TicketingController.class.getName());
	
	public static void seatManagement (Theater theater, int totalSeats, String seatArrangement, int rowCount) {
		
		String[] blockArray = seatArrangement.split(" ");
		Row row = new Row(rowCount);

		for (int sectionNum = 1; sectionNum <= blockArray.length; sectionNum++) {
			if (!blockArray.equals(" ")) {
				Section section = new Section(sectionNum);
				section.setCurrentAvailability(Integer.parseInt(blockArray[sectionNum - 1]));
				section.setTotalSeats(Integer.parseInt(blockArray[sectionNum - 1]));

				row.getSections().add(section);

				totalSeats = totalSeats	+ Integer.parseInt(blockArray[sectionNum - 1]);
			} else
				break;
		}
		theater.setTotalSeats(totalSeats);
		row.computeTotalSeats();
		row.computeAvailableSeats();
		theater.getRows().add(row);
	}
	
	public static void bookSeats(Theater theater, List<SeatRequest> allSeatRequests) {
		List<Row> rows = theater.getRows();

		for (SeatRequest emailRequest : allSeatRequests) {
			boolean found = false;
			for (Row row : rows) {
				if (!found && row.getCurrentAvailability() == row.getTotalSeats()) {
					for (Section section : row.getSections()) {
						if (!found && section.getCurrentAvailability() == emailRequest.getRequestedSeats()) {
							System.out.println(emailRequest.getName() + " Row "	+ row.getRowNumber() + " Section " + section.getSectionNumber());
							found = true;
							section.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							theater.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							break;
						} else if (!found && section.getCurrentAvailability() > emailRequest.getRequestedSeats()) {
							System.out.println(emailRequest.getName() + " Row "	+ row.getRowNumber() + " Section " + section.getSectionNumber());
							section.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							theater.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							found = true;
							break;
						}
					}
				} else if (!found && row.getCurrentAvailability() != row.getTotalSeats()) {
					for (Section section : row.getSections()) {
						if (!found &&section.getCurrentAvailability() == emailRequest.getRequestedSeats()) {
							System.out.println(emailRequest.getName() + " Row " + row.getRowNumber() + " Section " + section.getSectionNumber());
							found = true;
							section.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							theater.updateSeats(emailRequest.getRequestedSeats(), "SUB");
							break;
						}
					}
				}
				if (found)
					row.computeAvailableSeats();
			}
			if (!found) {
				if (theater.getTotalSeats() < emailRequest.getRequestedSeats()) {
					System.out.println(emailRequest.getName() + " "	+ TheaterConstants.REQUESTED_TOO_MANY);
					found=true;
				} else if (theater.getTotalSeats() >= emailRequest.getRequestedSeats()) {
					System.out.println(emailRequest.getName() + " " + TheaterConstants.SPLIT_REQUEST);
					found=true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(reader);
			int rowCount = 0;
			Theater theater = new Theater();
			int totalSeats = 0;
			System.out.println("Please load the theater seating arrangement. When done type end");
			while (true) {
				String seatArrangement = br.readLine();

				if (seatArrangement.equals("end")) {
					break;
				}
				// if the code reaches here then the value in the seatArrangement
				// should be either numbers or spaces.
				// add a check for above
				else {
					++rowCount;

					seatManagement(theater, totalSeats, seatArrangement, rowCount);
				}
			}
			theater.setTotalSeats(totalSeats);

			// read empty line
			br.readLine();

			// Now load the user requests
			System.out.println("Please load the seating requests :");
			List<SeatRequest> allSeatRequests = new LinkedList<SeatRequest>();
			while (true) {
				String seatRequest = br.readLine();
				if (seatRequest.equals("end")) {
					break;
				}
				// Do validation here. Check anything that comes to this point has
				// group of alphabets followed by space followed by any length
				// number like Smith 999 (means Smith needs 999 seats)
				else {
					String[] request = seatRequest.split(" ");
					SeatRequest emailRequest = new SeatRequest(request[0], Integer.parseInt(request[1]));
					allSeatRequests.add(emailRequest);
				}
			}
			bookSeats(theater, allSeatRequests);
		} catch (Exception e) {
			logger.info("Inventory Management process failed with " + e.getMessage());
			e.printStackTrace();
		}
	}
}