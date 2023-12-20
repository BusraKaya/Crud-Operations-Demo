package com.example.demo.response;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
@UtilityClass // Private constructor warn
public class ResponseHandler {
    public static GenericResponse generateResponse(String message, HttpStatus status, Object responseObj) {
        return GenericResponse.builder()
                .message(message)
                .status(status.name())
                .data(responseObj)
                .build();
    }

    public static GenericResponse generateResponse(HttpStatus status, Object responseObj) {
        return GenericResponse.builder()
                .status(status.name())
                .data(responseObj)
                .build();
    }

    public static GenericResponse generateResponse(Object responseObj) {
        return GenericResponse.builder()
                .data(responseObj)
                .build();
    }

    public static GenericResponse generateResponse(HttpStatus httpStatus) {
        return GenericResponse.builder()
                .status(httpStatus.name())
                .build();
    }

}
