package com.codecraftershub.telemedicine.exceptions;

public class DuplicateUserInfoException extends RuntimeException {
    public DuplicateUserInfoException(String message) {
        super(message);
    }
}
