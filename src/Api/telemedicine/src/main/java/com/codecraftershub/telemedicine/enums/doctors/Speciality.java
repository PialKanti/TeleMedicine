package com.codecraftershub.telemedicine.enums.doctors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Speciality {
    GENERAL_PHYSICIAN("General Physician"),
    GYNAE_OBS("Gynae & Obs");

    private final String displayValue;
}
