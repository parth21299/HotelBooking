package com.hb.project.dto;

public class Booking {
	private static int idCounter = 1;

	private int bookingId;
	private String userName;
	private String hotelName;

	public Booking(String userName, String hotelName) {
		this.bookingId = idCounter++;
		this.userName = userName;
		this.hotelName = hotelName;
	}

	public int getBookingId() {
		return bookingId;
	}

	public String getUserName() {
		return userName;
	}

	public String getHotelName() {
		return hotelName;
	}
}
