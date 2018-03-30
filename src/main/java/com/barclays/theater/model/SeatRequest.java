package main.java.com.barclays.theater.model;

public class SeatRequest {
	
	String name;
	int requestedSeats;
	
	public SeatRequest(String name, int requestedSeats) {
		this.name=name;
		this.requestedSeats=requestedSeats;
	}
	
	public SeatRequest() {
		super();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRequestedSeats() {
		return requestedSeats;
	}
	
	public void setRequestedSeats(int requestedSeats) {
		this.requestedSeats = requestedSeats;
	}
	
	@Override
	public String toString() {
		return "EmailRequest [name=" + name + ", requestedSeats=" + requestedSeats + "]";
	}
}
