package com.example.StaySync.exception;

public class RoomNotAvailableException extends RuntimeException {
    public RoomNotAvailableException(String message){
        super(message);
    }
}
