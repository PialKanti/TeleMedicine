package com.codecraftershub.telemedicine.dtos.responses.lookups;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LookupResponse {
    private List<LookupData> data;
}
