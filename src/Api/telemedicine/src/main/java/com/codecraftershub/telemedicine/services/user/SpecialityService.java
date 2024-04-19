package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityCreateRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityUpdateRequest;
import com.codecraftershub.telemedicine.dtos.responses.doctors.DoctorResponse;
import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.repositories.user.DoctorRepository;
import com.codecraftershub.telemedicine.repositories.user.SpecialityRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SpecialityService extends BaseService<Speciality, Long, SpecialityCreateRequest, SpecialityUpdateRequest, Speciality> {
    private final SpecialityRepository repository;
    private final DoctorRepository doctorRepository;

    public SpecialityService(SpecialityRepository repository, DoctorRepository doctorRepository) {
        super(repository);
        this.repository = repository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public <T> BasePaginatedResponse<T> findAll(Pageable pageable, Class<T> type) {
        var page = repository.findAllActive(pageable, type);

        return super.convertPageToResponse(page);
    }

    public BasePaginatedResponse<DoctorResponse> findAllDoctorsBySpeciality(Long specialityId, Pageable pageable) {
        var speciality = findById(specialityId, Speciality.class);

        var page = doctorRepository.findAllBySpecialitiesAndIsApprovedTrue(Collections.singletonList(speciality), pageable).map(entity ->
                new DoctorResponse(entity.getId(), entity.getTitle(), entity.getUser().getFirstName(), entity.getUser().getLastName(),
                        entity.getUser().getEmail(), entity.getUser().getPhoneNo(), entity.getSpecialities(), entity.getGender(),
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
    protected Speciality convertToCreateEntity(SpecialityCreateRequest request) {
        return Speciality
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();
    }

    @Override
    protected Speciality convertToUpdateEntity(Speciality entity, SpecialityUpdateRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setActive(request.getIsActive() != null ? request.getIsActive() : entity.isActive());

        return entity;
    }

    @Override
    protected Speciality convertToEntityResponse(Speciality entity) {
        return entity;
    }
}
