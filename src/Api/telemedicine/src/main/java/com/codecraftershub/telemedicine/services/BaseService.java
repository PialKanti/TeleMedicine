package com.codecraftershub.telemedicine.services;

import com.codecraftershub.telemedicine.dtos.BasePaginatedResponse;
import com.codecraftershub.telemedicine.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class BaseService<T, Id, CreateRequest, UpdateRequest, EntityResponse> {
    private final BaseRepository<T, Id> repository;

    public BaseService(BaseRepository<T, Id> repository) {
        this.repository = repository;
    }

    public <R> List<R> findAll(Class<R> type) {
        return repository.findAllBy(type);
    }

    public <R> BasePaginatedResponse<R> findAll(Pageable pageable, Class<R> type) {
        var page = getPage(pageable, type);

        return convertPageToResponse(page);
    }

    private <R> Page<R> getPage(Pageable pageable, Class<R> type) {
        return repository.findAllBy(pageable, type);
    }

    protected <R> BasePaginatedResponse<R> convertPageToResponse(Page<R> page) {
        return BasePaginatedResponse
                .<R>builder()
                .page(page.getNumber())
                .pageSize(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .data(page.getContent())
                .build();
    }

    public <R> R findById(Id id, Class<R> type) {
        return repository.findById(id, type).orElseThrow(EntityNotFoundException::new);
    }

    public EntityResponse create(CreateRequest request) {
        T entity = convertToCreateEntity(request);
        return convertToEntityResponse(repository.save(entity));
    }

    public EntityResponse update(Id id, UpdateRequest request) throws EntityNotFoundException {
        T entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        T updatedEntity = convertToUpdateEntity(entity, request);

        return convertToEntityResponse(repository.save(updatedEntity));
    }

    public void deleteById(Id id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }

    protected abstract T convertToCreateEntity(CreateRequest request);

    protected abstract T convertToUpdateEntity(T entity, UpdateRequest request);

    protected abstract EntityResponse convertToEntityResponse(T entity);
}
