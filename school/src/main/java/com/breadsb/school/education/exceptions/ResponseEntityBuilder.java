package com.breadsb.school.education.exceptions;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build (ApiError error) {
        return new ResponseEntity<>(error, error.getStatus());
    }
}