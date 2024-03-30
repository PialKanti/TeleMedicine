package com.codecraftershub.telemedicine.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    public final String displayValue;

    Gender(String displayValue) {
        this.displayValue = displayValue;
    }
}
