package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.auth.DoctorRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.DoctorUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.repositories.user.DoctorRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DoctorService extends BaseService<Doctor, Long, DoctorRegistrationRequest, DoctorUpdateRequest, UserResponse> {
    private final DoctorRepository repository;
    private final UserRepository userRepository;
    private final AppointmentService appointmentService;

    public DoctorService(DoctorRepository repository, UserRepository userRepository, AppointmentService appointmentService) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.appointmentService = appointmentService;
    }

    public Doctor update(Long id, Doctor entityToBeUpdated) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        return repository.save(entityToBeUpdated);
    }

    public <T> BasePaginatedResponse<T> findAllAppointmentsByIdAndDate(Long id, LocalDate appointmentDate, Pageable pageable, Class<T> type){
        var doctor = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return appointmentService.findAllByDoctorAndAppointmentDate(doctor, appointmentDate, pageable, type);
    }

    public <T> BasePaginatedResponse<T> findAllActiveAndApprovedDoctors(Pageable pageable, Class<T> type) {
        var page = repository.findAllActiveAndApprovedDoctors(pageable, type);
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
    protected Doctor convertToCreateEntity(DoctorRegistrationRequest request) {
        User user = userRepository.findByUsername(request.getUsername(), User.class).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Doctor
                .builder()
                .title(request.getTitle().toString())
                .speciality(request.getSpeciality().toString())
                .registrationNumber(request.getRegistrationNumber())
                .nidNumber(request.getNidNumber())
                .fee(request.getFee())
                .user(user)
                .build();
    }

    @Override
    protected Doctor convertToUpdateEntity(Doctor entity, DoctorUpdateRequest doctorUpdateRequest) {
        return Doctor.builder().build();
    }

    @Override
    protected UserResponse convertToEntityResponse(Doctor entity) {
        return UserResponse.builder().build();
    }
}
