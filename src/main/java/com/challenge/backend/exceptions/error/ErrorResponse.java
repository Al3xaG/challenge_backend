package com.challenge.backend.exceptions.error;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorResponse implements Serializable {
    private String message;
    private LocalDateTime date;
    private HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        date = LocalDateTime.now();
    }
}
