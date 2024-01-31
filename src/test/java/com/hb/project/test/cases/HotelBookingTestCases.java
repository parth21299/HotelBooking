package com.hb.project.test.cases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelBookingTestCases {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void createBooking() throws Exception {
		String requestBody = "{\"userName\": \"Parth \", \"hotelName\": \"Luxury Hotel\"}";

		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/bookings").content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.bookingId").isNumber())
				.andExpect(jsonPath("$.userName").value("Parth"))
				.andExpect(jsonPath("$.hotelName").value("Luxury Hotel"));

		// Extracting booking ID for further tests
		int bookingId = result.andReturn().getResponse().getContentAsString().contains("bookingId")
				? Integer.parseInt(
						result.andReturn().getResponse().getContentAsString().split(":")[1].split(",")[0].trim())
				: 0;

		// Verify booking was added
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookings")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].bookingId").value(bookingId))
				.andExpect(jsonPath("$[0].userName").value("Parth"))
				.andExpect(jsonPath("$[0].hotelName").value("Luxury Hotel"));
	}

	@Test
	public void getAllBookings() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookings")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
	}

	@Test
	public void cancelBooking() throws Exception {
		// Create a booking
		String requestBody = "{\"userName\": \"Puri\", \"hotelName\": \"Budget Inn\"}";
		ResultActions createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings").content(requestBody)
				.contentType(MediaType.APPLICATION_JSON));

		// Extracting booking ID for cancellation
		int bookingId = createResult.andReturn().getResponse().getContentAsString().contains("bookingId")
				? Integer.parseInt(
						createResult.andReturn().getResponse().getContentAsString().split(":")[1].split(",")[0].trim())
				: 0;

		// Cancel the booking
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/bookings/{bookingId}", bookingId))
				.andExpect(status().isOk()).andExpect(content().string("Booking canceled successfully."));

		// Verify booking is no longer present
		mockMvc.perform(MockMvcRequestBuilders.get("/api/bookings")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$").isEmpty());
	}
}