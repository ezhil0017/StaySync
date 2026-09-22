package com.example.StaySync.exception;

import com.example.StaySync.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(), OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
        String message=exception.getBindingResult().getFieldErrors().stream().map(error->error.getField()+ ":" +error.getDefaultMessage()).findFirst().orElse("Validation Failed");
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),message,OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(InvalidBookingDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDateException(InvalidBookingDateException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotFoundException(RoomNotFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleRoomNotAvailableException(RoomNotAvailableException exception){
        ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
