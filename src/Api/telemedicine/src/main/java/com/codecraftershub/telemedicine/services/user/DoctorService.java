package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.auth.DoctorRegistrationRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.DoctorUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.doctors.DoctorResponse;
import com.codecraftershub.telemedicine.dtos.responses.users.UserResponse;
import com.codecraftershub.telemedicine.entities.user.doctor.Doctor;
import com.codecraftershub.telemedicine.entities.user.User;
import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.repositories.user.DoctorRepository;
import com.codecraftershub.telemedicine.repositories.user.UserRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.codecraftershub.telemedicine.specifications.DoctorSpecification.isActive;
import static com.codecraftershub.telemedicine.specifications.DoctorSpecification.isApproved;

@Service
public class DoctorService extends BaseService<Doctor, Long, DoctorRegistrationRequest, DoctorUpdateRequest, UserResponse> {
    private final DoctorRepository repository;
    private final UserRepository userRepository;
    private final SpecialityService specialityService;

    public DoctorService(DoctorRepository repository, UserRepository userRepository, SpecialityService specialityService) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.specialityService = specialityService;
    }

    public Doctor update(Long id, Doctor entityToBeUpdated) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        return repository.save(entityToBeUpdated);
    }

    public BasePaginatedResponse<DoctorResponse> findAllActiveAndApprovedDoctors(Pageable pageable) {
        var specification = Specification.where(isActive(true)).and(isApproved(true));
        var page = repository.findAll(specification, pageable).map(entity ->
                new DoctorResponse(entity.getId(), entity.getTitle(), entity.getUser().getFirstName(), entity.getUser().getLastName(),
                        entity.getUser().getEmail(), entity.getUser().getPhoneNo(), entity.getSpeciality(), entity.getGender(),
                        entity.getRegistrationNumber(), entity.getNidNumber())
        );

        return BasePaginatedResponse
                .<DoctorResponse>builder()
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
                .speciality(Collections.singletonList(specialityService.findById(request.getSpecialityId(), Speciality.class)))
                .gender(request.getGender().toString())
                .registrationNumber(request.getRegistrationNumber())
                .nidNumber(request.getNidNumber())
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
