package com.codecraftershub.telemedicine.repositories.user;

import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Long> {
    boolean existsByAppointmentTime(LocalDateTime appointmentTime);
    <T> Page<T> findAllByDoctorAndAppointmentTimeAfter(Doctor doctor, LocalDateTime appointmentTime, Pageable pageable, Class<T> type);
    <T> Page<T> findAllByDoctorAndAppointmentTimeLessThanEqual(Doctor doctor, LocalDateTime appointmentTime, Pageable pageable, Class<T> type);
}
