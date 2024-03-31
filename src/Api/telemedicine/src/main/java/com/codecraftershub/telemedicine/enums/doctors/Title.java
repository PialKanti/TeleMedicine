package com.codecraftershub.telemedicine.enums.doctors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Title {
    DR("Dr."),
    PROFESSOR_DR("Prof. Dr."),
    ASSOCIATE_PROFESSOR_DR("Assoc. Prof. Dr."),
    ASSISTANT_PROFESSOR_DR("Asst. Prof. Dr.");

    private final String displayValue;
}
