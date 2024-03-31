package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.auth.DoctorRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.auth.LoginRequest;
import com.codecraftershub.telemedicine.dtos.auth.PatientRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.responses.auth.LoginResponse;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    private final AuthService service;
    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> registerAsPatient(@RequestBody PatientRegistrationRequest request) {
        return ResponseEntity.ok(service.registerAsPatient(request));
    }

    @PostMapping(value = "/register/doctor")
    public ResponseEntity<UserResponse> registerAsDoctor(@RequestBody DoctorRegistrationRequest request){
        return ResponseEntity.ok(service.registerAsDoctor(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
