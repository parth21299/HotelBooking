package com.hb.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.project.dto.Booking;
import com.hb.project.dto.BookingRequest;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	private List<Booking> bookings = new ArrayList<>();

	// Creating a new booking for a user
	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
		Booking newBooking = new Booking(bookingRequest.getUserName(), bookingRequest.getHotelName());
		bookings.add(newBooking);
		return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
	}
	// Getting all the booking details (If we need to get the booking of particular user new can do it by getting it from bookingId )
	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		return new ResponseEntity<>(new ArrayList<>(bookings), HttpStatus.OK);
	}
	// Canceling the booking from bookingId
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
		boolean canceled = bookings.removeIf(booking -> booking.getBookingId() == bookingId);
		if (canceled) {
			return new ResponseEntity<>("Booking canceled successfully.", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Booking not found or could not be canceled.", HttpStatus.NOT_FOUND);
	}
}
