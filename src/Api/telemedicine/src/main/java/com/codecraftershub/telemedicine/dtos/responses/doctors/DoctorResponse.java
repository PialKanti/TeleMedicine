package com.codecraftershub.telemedicine.dtos.responses.doctors;

import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;

import java.util.List;

public record DoctorResponse(Long id, String title, String firstName, String lastName, String email, String phoneNo,
                             List<Speciality> speciality, String gender, String registrationNumber, String nidNumber) {
}
