package com.breadsb.school.education.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        List<String> details = new ArrayList<>();
        details.add(e.getMessage());

        ApiError ae = new ApiError(
                LocalDateTime.now(),
                "Resource not found",
                HttpStatus.NOT_FOUND,
                details
        );

        return ResponseEntityBuilder.build(ae);
    }
}