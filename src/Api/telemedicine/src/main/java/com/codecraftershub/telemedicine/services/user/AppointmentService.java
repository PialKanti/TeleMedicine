package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.requests.users.AppointmentCreateRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.AppointmentUpdateRequest;
import com.codecraftershub.telemedicine.entities.user.Appointment;
import com.codecraftershub.telemedicine.entities.user.Patient;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.exceptions.AppointmentConflictException;
import com.codecraftershub.telemedicine.repositories.user.AppointmentRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService extends BaseService<Appointment, Long, AppointmentCreateRequest, AppointmentUpdateRequest, Appointment> {
    private final AppointmentRepository repository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository repository, DoctorService doctorService, PatientService patientService) {
        super(repository);
        this.repository = repository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Override
    public Appointment create(AppointmentCreateRequest createRequest) {
        if (repository.existsByAppointmentTime(createRequest.getAppointmentTime())) {
            throw new AppointmentConflictException("Doctor already has an appointment scheduled at that time");
        }
        return super.create(createRequest);
    }

    public <T> BasePaginatedResponse<T> searchDoctorAppointments(Long doctorId, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable, Class<T> type) {
        var doctor = doctorService.findById(doctorId, Doctor.class);
        var page = repository.findAllByDoctorAndAppointmentTimeBetween(doctor, fromDate, toDate, pageable, type);
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
                .doctor(doctorService.findById(createRequest.getDoctorId(), Doctor.class))
                .patient(patientService.findById(createRequest.getPatientId(), Patient.class))
                .appointmentTime(createRequest.getAppointmentTime())
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
