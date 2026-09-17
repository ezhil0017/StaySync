package com.example.StaySync.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

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
}
