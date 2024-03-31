package com.codecraftershub.telemedicine.dtos.auth;

import com.codecraftershub.telemedicine.enums.doctors.Speciality;
import com.codecraftershub.telemedicine.enums.doctors.Title;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRegistrationRequest extends RegistrationRequest{
    private Title title;
    private Speciality speciality;
    private String registrationNumber;
    private String nidNumber;
    private double fee;
}
