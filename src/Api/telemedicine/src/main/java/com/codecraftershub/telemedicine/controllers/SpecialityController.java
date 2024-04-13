package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityCreateRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityUpdateRequest;
import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.services.user.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/specialities")
@RequiredArgsConstructor
public class SpecialityController {
    private final SpecialityService service;

    @GetMapping
    public ResponseEntity<BasePaginatedResponse<Speciality>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.findAll(pageable, Speciality.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Speciality> findByID(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id, Speciality.class));
    }

    @PostMapping
    public ResponseEntity<Speciality> create(@RequestBody SpecialityCreateRequest request) {
        var cratedEntity = service.create(request);
        String uriString = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cratedEntity.getId())
                .toUriString();

        return ResponseEntity.created(URI.create(uriString)).body(cratedEntity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Speciality> update(@PathVariable Long id, @RequestBody SpecialityUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
