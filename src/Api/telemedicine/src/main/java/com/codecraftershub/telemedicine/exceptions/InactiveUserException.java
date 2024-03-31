package com.codecraftershub.telemedicine.exceptions;

public class InactiveUserException extends  RuntimeException{
    public InactiveUserException(String message) {
        super(message);
    }
}
