package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.auth.PatientRegistrationRequest;
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
}
