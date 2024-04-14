package com.codecraftershub.telemedicine.dtos.auth;

import com.codecraftershub.telemedicine.enums.Gender;
import com.codecraftershub.telemedicine.enums.doctors.Title;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRegistrationRequest extends RegistrationRequest {
    private Title title;
    private Long specialityId;
    private Gender gender;
    private String registrationNumber;
    private String nidNumber;
}
