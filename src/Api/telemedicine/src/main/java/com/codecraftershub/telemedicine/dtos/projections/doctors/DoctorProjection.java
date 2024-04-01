package com.codecraftershub.telemedicine.dtos.projections.doctors;

public interface DoctorProjection {
    Long getId();
    String getTitle();
    String getSpeciality();
    String getRegistrationNumber();
    String getNidNumber();
    Double getFee();
    User getUser();

    interface User{
        String getFirstName();
        String getLastName();
        String getPhoneNo();
    }
}
