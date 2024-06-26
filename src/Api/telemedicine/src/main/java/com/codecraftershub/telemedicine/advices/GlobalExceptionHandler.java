package com.codecraftershub.telemedicine.advices;

import com.codecraftershub.telemedicine.dtos.responses.errors.ErrorResponse;
import com.codecraftershub.telemedicine.exceptions.AppointmentConflictException;
import com.codecraftershub.telemedicine.exceptions.DuplicateUserInfoException;
import com.codecraftershub.telemedicine.exceptions.InactiveUserException;
import com.codecraftershub.telemedicine.exceptions.PasswordMismatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.build(HttpStatus.UNAUTHORIZED, "Username or password is incorrect"));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.build(HttpStatus.NOT_FOUND, "Entity not found"));
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.build(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(value = {PasswordMismatchException.class})
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.build(HttpStatus.UNAUTHORIZED, exception.getMessage()));
    }

    @ExceptionHandler(value = {InactiveUserException.class})
    public ResponseEntity<ErrorResponse> handleInactiveUserException(InactiveUserException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.build(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

    @ExceptionHandler(value = {AppointmentConflictException.class})
    public ResponseEntity<ErrorResponse> handleAppointmentConflictException(AppointmentConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.build(HttpStatus.CONFLICT, exception.getMessage()));
    }

    @ExceptionHandler(value = {DuplicateUserInfoException.class})
    public ResponseEntity<ErrorResponse> handleDuplicateUserInfoException(DuplicateUserInfoException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.build(HttpStatus.CONFLICT, exception.getMessage()));
    }
}
