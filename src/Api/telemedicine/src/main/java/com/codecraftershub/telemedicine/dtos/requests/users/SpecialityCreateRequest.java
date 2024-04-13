package com.codecraftershub.telemedicine.dtos.requests.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpecialityCreateRequest {
    private String name;
    private String description;
}
