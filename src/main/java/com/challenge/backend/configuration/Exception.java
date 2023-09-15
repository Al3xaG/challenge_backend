package com.challenge.backend.configuration;

import com.challenge.backend.exceptions.NotFoundException;
import com.challenge.backend.exceptions.TransactionException;
import com.challenge.backend.exceptions.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class Exception {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e, HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> notFoundException(TransactionException e, HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
