package main.java.com.barclays.theater.model;

public class Section {
	int sectionNumber;
	int totalSeats;
	int currentAvailability;

	public Section(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
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

	@Override
	public String toString() {
		return "Section [sectionNumber=" + sectionNumber + ", totalSeats="
				+ totalSeats + ", currentAvailability=" + currentAvailability
				+ "]";
	}

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
}