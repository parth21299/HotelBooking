Project Overview: Hotel Booking System using Spring Boot

1. Purpose:
   - Objective: To develop a robust Hotel Booking System that supports creating, viewing, and canceling bookings via RESTful APIs.

2. Technology Stack:
   - Framework: Spring Boot
   - Language: Java
   - Testing: JUnit, Spring Boot Test, MockMvc

3. Components:
   - BookingController: Manages REST endpoints for bookings.
   - Booking, BookingRequest: POJOs representing a booking and request

4. API Endpoints:
   - POST /api/bookings: Create a new booking.
   - GET /api/bookings: Retrieve all bookings.
   - DELETE /api/bookings/{bookingId}: Cancel a booking by ID.

5. Data Management:
   - Data Storage: In-memory list for storing bookings.
   - Optimizations: Indexing, caching, and load balancing for enhanced performance.

6. Testing:
   - JUnit Tests: Happy path tests for creating, retrieving, and canceling bookings.
   - MockMvc: Integration tests for API endpoints.

7. Security:
   - HTTPS: Secure communication using HTTPS.

9. Documentation:
   - API Documentation: Clearly document API endpoints, request, and response formats.
   - Code Comments: In-code comments for clarity and maintainability.
