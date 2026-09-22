package com.example.StaySync.response;

import java.time.OffsetDateTime;

public class ApiResponse <T>{
    private  int status;
    private String message;
    private  T data;
    private OffsetDateTime timestamp;
    private boolean success;

    public ApiResponse(int status, String message, T data, OffsetDateTime timestamp, boolean success) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
        this.success = success;
    }

    public ApiResponse() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
