package com.codecraftershub.telemedicine.dtos.auth;

import com.codecraftershub.telemedicine.enums.BloodGroup;
import com.codecraftershub.telemedicine.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientRegistrationRequest extends RegistrationRequest{
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private BloodGroup bloodGroup;
    private Gender gender;
    private String address;
}
