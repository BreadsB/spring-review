package com.breadsb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GeneralApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( {ResourceNotFoundException.class} )
    @ResponseBody
    ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException e) {

        ApiErrorDetails aed = new ApiErrorDetails(
                LocalDateTime.now(),
                "Custom message",
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );

        return new ResponseEntity<>(aed, aed.getStatus());
    }
}