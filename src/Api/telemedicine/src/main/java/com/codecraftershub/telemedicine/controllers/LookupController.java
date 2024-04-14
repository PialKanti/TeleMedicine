package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.responses.lookups.LookupData;
import com.codecraftershub.telemedicine.dtos.responses.lookups.LookupResponse;
import com.codecraftershub.telemedicine.services.LookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lookups")
@RequiredArgsConstructor
public class LookupController {
    private final LookupService service;

    @GetMapping(value = "/bloodgroup")
    public ResponseEntity<LookupResponse> getBloodGroups() {
        return ResponseEntity.ok(service.getBloodGroups());
    }

    @GetMapping(value = "/gender")
    public ResponseEntity<LookupResponse> getGenders() {
        return ResponseEntity.ok(service.getGenders());
    }

}
