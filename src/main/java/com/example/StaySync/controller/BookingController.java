package com.example.StaySync.controller;

import com.example.StaySync.dto.CreateBookingRequest;
import com.example.StaySync.response.ApiResponse;
import com.example.StaySync.response.BookingResponse;
import com.example.StaySync.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        System.out.println("Booking Service Created");
        this.bookingService = bookingService;
    }

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request){
        BookingResponse response= bookingService.createBooking(request);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(response);
        apiResponse.setMessage("Booking Created Successfully");
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setSuccess(true);
        apiResponse.setTimestamp(OffsetDateTime.now());

        return apiResponse;
    }
}
