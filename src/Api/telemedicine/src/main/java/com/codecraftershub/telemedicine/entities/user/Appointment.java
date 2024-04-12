package com.codecraftershub.telemedicine.entities.user;

import com.codecraftershub.telemedicine.entities.common.AuditableEntity;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Appointment extends AuditableEntity {
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;
    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;
    private String reason;
}
