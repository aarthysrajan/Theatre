package test.java.com.barclays.inventorymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.com.barclays.theater.controller.TicketingController;
import main.java.com.barclays.theater.model.SeatRequest;
import main.java.com.barclays.theater.model.Theater;

public class TicketingTest {

	@SuppressWarnings("static-access")
	@Test
	public void testTicketing() {
		
		List<String> seatArrangement = new ArrayList<String>();
		seatArrangement.add("6 6");
		seatArrangement.add("3 5 5 3");
		seatArrangement.add("4 6 6 4");
		seatArrangement.add("2 8 8 2");
		seatArrangement.add("6 6");
		
		// Call the ticketing method
		TicketingController ticketingController = new TicketingController();
		Theater theater = new Theater();
		theater.setTotalSeats(0);
		for (int rowCount=0; rowCount<seatArrangement.size(); rowCount++) {
			ticketingController.seatManagement(theater, theater.getTotalSeats(), seatArrangement.get(rowCount), rowCount+1);
		}
		
		List<SeatRequest> allSeatRequests = this.setupSeatRequest();
		ticketingController.bookSeats(theater, allSeatRequests);
	}
	
	public List<SeatRequest> setupSeatRequest() {
		
		List<SeatRequest> seatRequest = new ArrayList<SeatRequest>();
		
		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setName("Smith");
		seatRequest1.setRequestedSeats(2);
		
		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setName("Jones");
		seatRequest2.setRequestedSeats(5);
		
		SeatRequest seatRequest3 = new SeatRequest();
		seatRequest3.setName("David");
		seatRequest3.setRequestedSeats(6);
		
		SeatRequest seatRequest4 = new SeatRequest();
		seatRequest4.setName("Wilson");
		seatRequest4.setRequestedSeats(100);
		
		SeatRequest seatRequest5 = new SeatRequest();
		seatRequest5.setName("Johnson");
		seatRequest5.setRequestedSeats(3);
		
		SeatRequest seatRequest6 = new SeatRequest();
		seatRequest6.setName("Williams");
		seatRequest6.setRequestedSeats(4);
		
		SeatRequest seatRequest7 = new SeatRequest();
		seatRequest7.setName("Brown");
		seatRequest7.setRequestedSeats(8);
		
		SeatRequest seatRequest8 = new SeatRequest();
		seatRequest8.setName("Miller");
		seatRequest8.setRequestedSeats(12);
		
		seatRequest.add(seatRequest1);
		seatRequest.add(seatRequest2);
		seatRequest.add(seatRequest3);
		seatRequest.add(seatRequest4);
		seatRequest.add(seatRequest5);
		seatRequest.add(seatRequest6);
		seatRequest.add(seatRequest7);
		seatRequest.add(seatRequest8);
		
		return seatRequest;
	}
}
