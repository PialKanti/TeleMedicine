package com.codecraftershub.telemedicine.dtos.auth;

import com.codecraftershub.telemedicine.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNo;
    @JsonIgnore
    private List<UserRole> roles;
}
