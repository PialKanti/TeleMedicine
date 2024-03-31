package com.codecraftershub.telemedicine.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("Admin"),
    DOCTOR("Doctor"),
    PATIENT("Patient");

    private final String displayValue;
}
