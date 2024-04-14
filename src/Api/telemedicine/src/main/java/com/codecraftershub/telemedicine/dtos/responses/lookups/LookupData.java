package com.codecraftershub.telemedicine.dtos.responses.lookups;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LookupData {
    private String name;
    private String code;
}
