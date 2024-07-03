package com.planonsoftware;

import java.time.LocalDate;
import java.time.Period;

public class Book {
	
	String title;
	String author;
	int copiesAvailable;
	double price;
	LocalDate borrowDate;
	boolean isRented;
	static final int RENT_PERIOD_DAYS=14;
	static final double DAILY_LATE_FEE=2.0;
	
	public Book(String title,String author,int copiesAvailable,double price)
	{
	  this.title=title;
	  this.author=author;
	  this.copiesAvailable=copiesAvailable;
	  this.price=price;
	  this.isRented=false;

}
	public double returnBook()
	{
		if(isRented)
		{
			isRented=false;
			LocalDate returnDate=LocalDate.now();
			Period period=Period.between(borrowDate, returnDate);
			long daysRented=period.getDays();
			long lateDays=daysRented-RENT_PERIOD_DAYS;
			return lateDays>0?lateDays*DAILY_LATE_FEE:0.0;
		}
		return 0.0;
	}
}
