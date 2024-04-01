package com.codecraftershub.telemedicine.dtos.responses.doctors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApprovalResponse {
    private long id;
    private String username;
}
