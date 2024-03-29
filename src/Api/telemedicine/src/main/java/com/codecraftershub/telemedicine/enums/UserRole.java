package com.codecraftershub.telemedicine.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    DOCTOR("Doctor"),
    PATIENT("Patient");

    private final String displayValue;

    UserRole(String displayValue) {
        this.displayValue = displayValue;
    }
}
