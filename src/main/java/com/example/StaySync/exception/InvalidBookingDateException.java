package com.example.StaySync.exception;

public class InvalidBookingDateException extends RuntimeException{
    public InvalidBookingDateException(){
        super("Invalid Booking Date");
    }
}
