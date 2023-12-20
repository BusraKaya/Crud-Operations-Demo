package com.example.demo.exception;

public class DemoAlreadyExistsException extends RuntimeException{
    public DemoAlreadyExistsException(String message){
        super(message);
    }

}
