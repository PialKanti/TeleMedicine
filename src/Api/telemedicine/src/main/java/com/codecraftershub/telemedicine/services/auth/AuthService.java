package com.codecraftershub.telemedicine.services.auth;

import com.codecraftershub.telemedicine.dtos.auth.PatientRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.services.user.PatientService;
import com.codecraftershub.telemedicine.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PatientService patientService;

    public UserResponse registerAsPatient(PatientRegistrationRequest request){
        UserResponse response = userService.create(request);
        patientService.create(request);

        return response;
    }
}