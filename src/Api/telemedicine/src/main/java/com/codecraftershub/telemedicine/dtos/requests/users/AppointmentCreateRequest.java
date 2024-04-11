package com.codecraftershub.telemedicine.dtos.requests.users;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentCreateRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
}
