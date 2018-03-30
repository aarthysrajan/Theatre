package main.java.com.barclays.theater.model;

import java.util.LinkedList;
import java.util.List;

public class Theater {
	List<Row> rows;
	int totalSeats;
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public List<Row> getRows() {
		if(rows==null)
		{
			rows=new LinkedList<Row>();
		}
		return rows;
	}
	
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	//Create an exception called UnsupportedOperation
	public int updateSeats(int count, String action) //throws UnsupportedOperationException
	{
		if (action.equals("ADD"))
		{
			this.totalSeats=this.totalSeats+count;
		}
		else if (action.equals("SUB"))
		{  //check if after subtraction if the total seats count goes to zero then we should throw an exception 
			this.totalSeats=this.totalSeats-count;
		}
		
		return this.totalSeats;
	}
	
	@Override
	public String toString() {
		return "Theater [rows=" + rows + ", totalSeats=" + totalSeats + "]";
	}
}