package com.example.StaySync.service;

import com.example.StaySync.repository.BookingRepository;
import com.example.StaySync.repository.GuestRepository;

public class BookingService {
    private BookingRepository bookingRepository;
    private GuestRepository guestRepository;

    public BookingService(BookingRepository bookingRepository, GuestRepository guestRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
    }
}
