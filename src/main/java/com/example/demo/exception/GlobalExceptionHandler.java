package com.example.demo.exception;

import com.example.demo.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DemoNotFoundException.class)
    public ResponseEntity<Object> handleDemoNotFoundException (DemoNotFoundException demoNotFoundException){
        GenericResponse genericResponse =  GenericResponse.builder()
                .message(demoNotFoundException.getMessage())
                .build();

        return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
