package com.example.StaySync.exception;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(Long id){
        super("Room not available: "+id);
    }
}
