package com.codecraftershub.telemedicine.services.user;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityCreateRequest;
import com.codecraftershub.telemedicine.dtos.requests.users.SpecialityUpdateRequest;
import com.codecraftershub.telemedicine.entities.user.doctor.Speciality;
import com.codecraftershub.telemedicine.repositories.user.SpecialityRepository;
import com.codecraftershub.telemedicine.services.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService extends BaseService<Speciality, Long, SpecialityCreateRequest, SpecialityUpdateRequest, Speciality> {
    private final SpecialityRepository repository;

    public SpecialityService(SpecialityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public <T> BasePaginatedResponse<T> findAll(Pageable pageable, Class<T> type) {
        var page = repository.findAllActive(pageable, type);

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
    protected Speciality convertToCreateEntity(SpecialityCreateRequest request) {
        return Speciality
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .isActive(true)
                .build();
    }

    @Override
    protected Speciality convertToUpdateEntity(Speciality entity, SpecialityUpdateRequest request) {
        return Speciality
                .builder()
                .description(request.getDescription())
                .isActive(request.getIsActive() != null ? request.getIsActive() : entity.isActive())
                .build();
    }

    @Override
    protected Speciality convertToEntityResponse(Speciality entity) {
        return entity;
    }
}
