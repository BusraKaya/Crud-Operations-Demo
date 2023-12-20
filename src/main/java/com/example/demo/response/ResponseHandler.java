package com.example.demo.response;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
@UtilityClass // Objesi olmaması gereken full statik methodları içeren sınıflarda private const uyarısı verdi.
public class ResponseHandler {
    public static GenericResponse<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        return GenericResponse.builder()
                .message(message)
                .status(status.name())
                .data(responseObj)
                .build();
    }

    public static GenericResponse<Object> generateResponse(HttpStatus status, Object responseObj) {
        return GenericResponse.builder()
                .status(status.name())
                .data(responseObj)
                .build();
    }

    public static GenericResponse<Object> generateResponse(Object responseObj) {
        return GenericResponse.builder()
                .data(responseObj)
                .build();
    }

    public static GenericResponse<Object> generateResponse(HttpStatus httpStatus) {
        return GenericResponse.builder()
                .status(httpStatus.name())
                .build();
    }

}
