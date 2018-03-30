package main.java.com.barclays.theater.model;

import java.util.LinkedList;
import java.util.List;

public class Row {
	
	int rowNumber;
	int totalSeats;
	int currentAvailability;
	List<Section> sections;

	public Row(int rowNumber) {
		this.rowNumber=rowNumber;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getCurrentAvailability() {
		return currentAvailability;
	}

	public void setCurrentAvailability(int currentAvailability) {
		this.currentAvailability = currentAvailability;
	}

	public List<Section> getSections() {
		if(sections==null)
		{
			sections=new LinkedList<Section>();
		}
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void computeTotalSeats() {

		for (Section section : sections) {
			this.totalSeats = this.totalSeats + section.getTotalSeats();
		}
	}
	
	public void computeAvailableSeats(){
		for (Section section : sections) {
		this.currentAvailability = this.currentAvailability
				+ section.getCurrentAvailability();
		}
	}

	// see if the method in Section and Theater can be combined using a helper
	// class
	public void updateSeats(int count, String action) // throws
														// UnsupportedOperationException
	{
		if (action.equals("ADD")) {
			this.currentAvailability = this.currentAvailability + count;
		} else if (action.equals("SUB")) { // check if after subtraction if the
											// total seats count goes to zero
											// then we should throw an exception
			this.currentAvailability = this.currentAvailability - count;
		}
	}

	@Override
	public String toString() {
		return "Row [rowNumber=" + rowNumber + ", totalSeats=" + totalSeats
				+ ", currentAvailability=" + currentAvailability
				+ ", sections=" + sections + "]"+"\n";
	}
}