package com.codecraftershub.telemedicine.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasePaginatedResponse<T> {
    private int page;
    private int pageSize;
    private long totalItems;
    private int totalPages;
    private List<T> data;
}
