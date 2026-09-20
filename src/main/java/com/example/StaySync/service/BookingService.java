package com.example.StaySync.service;

import com.example.StaySync.entity.Guest;
import com.example.StaySync.entity.Room;
import com.example.StaySync.enums.BookingStatus;
import com.example.StaySync.exception.GuestNotFoundException;
import com.example.StaySync.exception.ResourceNotFoundException;
import com.example.StaySync.exception.RoomNotAvailableException;
import com.example.StaySync.repository.BookingRepository;
import com.example.StaySync.repository.GuestRepository;
import com.example.StaySync.repository.RoomRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class BookingService {
    private BookingRepository bookingRepository;
    private GuestRepository guestRepository;
    private RoomRepository roomRepository;


    public BookingService(BookingRepository bookingRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    public Guest findGuest(Long guestId){
        return guestRepository.findById(guestId).orElseThrow(()->new GuestNotFoundException("Guest Not Found"));
    }

    public Room findRoom(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room not available"));
    }
    public void checkRoomAvailability(Long roomId, OffsetDateTime checkIn, OffsetDateTime checkOut)
    {
        boolean isAlreadyBooked=bookingRepository.existsOverlappingBooking(roomId,checkIn,checkOut, BookingStatus.CANCELLED);
    if(isAlreadyBooked){
        throw new RoomNotAvailableException("Room Not Available for Booking");
    }
    }
    public BigDecimal calculateBookingPrice(Room room,OffsetDateTime checkIn,OffsetDateTime checkOut){
        long numberOfNights= ChronoUnit.DAYS.between(
                checkIn.toLocalDate(),
                checkOut.toLocalDate()
        );
        BigDecimal basePrice=room.getRoomType().getBasePrice();
        return basePrice.multiply(BigDecimal.valueOf(numberOfNights));
    }



}
