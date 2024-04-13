package com.codecraftershub.telemedicine.dtos.requests.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialityUpdateRequest {
    private String description;
    private Boolean isActive;
}
