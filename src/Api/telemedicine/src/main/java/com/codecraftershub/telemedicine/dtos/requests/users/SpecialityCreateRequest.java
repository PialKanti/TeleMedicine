package com.codecraftershub.telemedicine.dtos.requests.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpecialityCreateRequest {
    private String name;
    private String description;
    @JsonProperty("active")
    private Boolean isActive;
}
