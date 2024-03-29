package com.codecraftershub.telemedicine.dtos.requests.users;

import com.codecraftershub.telemedicine.enums.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private List<UserRole> roles;
}
