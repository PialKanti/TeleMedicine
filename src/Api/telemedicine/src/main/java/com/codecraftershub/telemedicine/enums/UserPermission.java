package com.codecraftershub.telemedicine.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserPermission {
    DOCTOR_READ("Doctor-Read"),
    DOCTOR_CREATE("Doctor-Create"),
    DOCTOR_UPDATE("Doctor-Update"),
    DOCTOR_DELETE("Doctor-Delete"),

    PATIENT_READ("Patient-Read"),
    PATIENT_CREATE("Patient-Create"),
    PATIENT_UPDATE("Patient-Update"),
    PATIENT_DELETE("Patient-Delete");

    private final String displayValue;
}
