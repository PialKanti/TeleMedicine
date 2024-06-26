package com.codecraftershub.telemedicine.controllers;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.DoctorSearchCriteria;
import com.codecraftershub.telemedicine.dtos.projections.doctors.DoctorProjection;
import com.codecraftershub.telemedicine.dtos.responses.GenericResponse;
import com.codecraftershub.telemedicine.dtos.responses.doctors.ApprovalResponse;
import com.codecraftershub.telemedicine.dtos.responses.doctors.DoctorResponse;
import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.services.user.AppointmentService;
import com.codecraftershub.telemedicine.services.user.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService service;
    private final AppointmentService appointmentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorProjection> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id, DoctorProjection.class));
    }

    @GetMapping
    public ResponseEntity<BasePaginatedResponse<DoctorResponse>> findAll(@RequestParam(name = "approved", required = false) Boolean approved,
                                                                         @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                         @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        var criteria = new DoctorSearchCriteria(approved);

        return ResponseEntity.ok(service.findAllByCriteria(criteria, pageable));
    }

    @GetMapping(value = "/{id}/approve")
    public ResponseEntity<GenericResponse<ApprovalResponse>> approve(@PathVariable(name = "id") Long id) {
        var doctor = service.findById(id, Doctor.class);
        doctor.setApproved(true);
        service.update(id, doctor);

        return ResponseEntity.ok(GenericResponse.<ApprovalResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor with ID %s has been successfully approved".formatted(doctor.getId()))
                .data(ApprovalResponse.builder().id(doctor.getId()).username(doctor.getUser().getUsername()).build())
                .build());
    }

    @GetMapping(value = "/{id}/appointments/upcoming")
    public ResponseEntity<BasePaginatedResponse<Appointment>> findAllUpcomingAppointments(@PathVariable(name = "id") Long id,
                                                                                          @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                                          @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(appointmentService.getDoctorUpcomingAppointments(id, pageable, Appointment.class));
    }

    @GetMapping(value = "/{id}/appointments/histories")
    public ResponseEntity<BasePaginatedResponse<Appointment>> findAllAppointmentHistories(@PathVariable(name = "id") Long id,
                                                                                          @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                                          @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(appointmentService.getDoctorAppointmentHistories(id, pageable, Appointment.class));
    }
}
