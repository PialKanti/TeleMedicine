package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.requests.users.AppointmentCreateRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.AppointmentUpdateRequest;
import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.entities.user.Patient;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.exceptions.AppointmentConflictException;
import com.codecraftershub.telemedicine.repositories.user.AppointmentRepository;
import com.codecraftershub.telemedicine.repositories.user.DoctorRepository;
import com.codecraftershub.telemedicine.repositories.user.PatientRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppointmentService extends BaseService<Appointment, Long, AppointmentCreateRequest, AppointmentUpdateRequest, Appointment> {
    private final AppointmentRepository repository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository repository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        super(repository);
        this.repository = repository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Appointment create(AppointmentCreateRequest createRequest) {
        if (repository.existsByAppointmentDateAndAppointmentTime(createRequest.getAppointmentDate(), createRequest.getAppointmentTime())) {
            throw new AppointmentConflictException("Doctor already has an appointment scheduled at that time");
        }
        return super.create(createRequest);
    }

    public <T> BasePaginatedResponse<T> findAllByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDate fromDate, LocalDate toDate, Pageable pageable, Class<T> type) {
        var page = repository.findAllByDoctorAndAppointmentDateBetween(doctor, fromDate, toDate, pageable, type);
        return BasePaginatedResponse
                .<T>builder()
                .page(page.getNumber())
                .pageSize(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .data(page.getContent())
                .build();
    }

    @Override
    protected Appointment convertToCreateEntity(AppointmentCreateRequest createRequest) {
        return Appointment
                .builder()
                .doctor(doctorRepository.findById(createRequest.getDoctorId(), Doctor.class).orElseThrow(EntityExistsException::new))
                .patient(patientRepository.findById(createRequest.getPatientId(), Patient.class).orElseThrow(EntityExistsException::new))
                .appointmentDate(createRequest.getAppointmentDate())
                .appointmentTime(createRequest.getAppointmentTime())
                .reason(createRequest.getReason())
                .build();
    }

    @Override
    protected Appointment convertToUpdateEntity(Appointment entity, AppointmentUpdateRequest appointmentUpdateRequest) {
        return null;
    }

    @Override
    protected Appointment convertToEntityResponse(Appointment entity) {
        return null;
    }
}
