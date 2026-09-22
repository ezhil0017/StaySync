package com.example.StaySync.response;

import com.example.StaySync.enums.BookingStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class BookingResponse {
    private Long bookingId;
    private Long guestId;
    private Long roomId;
    private OffsetDateTime checkIn;
    private OffsetDateTime checkOut;
    private Integer numberOfGuests;
    private BigDecimal price;
    private BookingStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
