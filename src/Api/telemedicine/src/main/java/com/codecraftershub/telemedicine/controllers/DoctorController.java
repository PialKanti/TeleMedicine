package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.entities.user.Doctor;
import com.codecraftershub.telemedicine.services.user.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService service;

    @GetMapping(value = "/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable(name = "id") Long id) {
        var doctor = service.findById(id, Doctor.class);
        doctor.setApproved(true);
        service.update(id, doctor);

        return ResponseEntity.ok("Doctor approved");
    }
}
