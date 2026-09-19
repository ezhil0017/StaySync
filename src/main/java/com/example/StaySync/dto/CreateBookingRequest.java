package com.example.StaySync.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public class CreateBookingRequest {

    @NotNull(message = "Guest Id is Required")
    private Long guestId;

    @NotNull(message = "Room Id is Required")
    private Long roomId;

    @NotNull(message = "Check In Date is Required")
    @Future(message ="Check In Date must be future")
    private OffsetDateTime checkIn;

    @NotNull(message = "Check In Date is Required")
    @Future(message ="Check In Date must be future")
    private OffsetDateTime checkOut;

    @Positive(message = "Number of guests should be morethan 0")
    @NotNull(message = "Number of guests is required")
    private Integer numberOfGuests;


    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public OffsetDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(OffsetDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public OffsetDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(OffsetDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
