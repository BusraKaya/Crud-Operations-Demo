package com.example.demo.exception;

public class DemoNotFoundException extends RuntimeException{
    public DemoNotFoundException(String message){
        super(message);
    }
}
