package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.requests.users.AppointmentCreateRequest;
import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.services.user.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<Appointment> create(AppointmentCreateRequest createRequest) {
        return ResponseEntity.ok(service.create(createRequest));
    }
}
