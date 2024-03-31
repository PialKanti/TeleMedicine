package com.codecraftershub.telemedicine.services.auth;

import com.codecraftershub.telemedicine.dtos.auth.DoctorRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.auth.LoginRequest;
import com.codecraftershub.telemedicine.dtos.auth.PatientRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.responses.auth.LoginResponse;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.services.user.DoctorService;
import com.codecraftershub.telemedicine.services.user.PatientService;
import com.codecraftershub.telemedicine.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserResponse registerAsPatient(PatientRegistrationRequest request){
        UserResponse response = userService.create(request);
        patientService.create(request);

        return response;
    }

    public UserResponse registerAsDoctor(DoctorRegistrationRequest request){
        UserResponse response = userService.create(request);
        doctorService.create(request);
        return response;
    }

    public LoginResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(user);
        return LoginResponse
                .builder()
                .accessToken(accessToken)
                .build();
    }
}
