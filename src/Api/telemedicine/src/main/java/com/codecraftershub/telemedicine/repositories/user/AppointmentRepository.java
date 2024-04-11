package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Long> {
    boolean existsByAppointmentTime(LocalDateTime appointmentTime);
}
