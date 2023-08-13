package com.breadsb.school.education.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy hh:mm:ss")
    private LocalDateTime dateTime;
    private String message;
    private HttpStatus status;
    private List<String> errors;
}
