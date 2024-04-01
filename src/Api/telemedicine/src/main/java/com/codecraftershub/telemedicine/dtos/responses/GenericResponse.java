package com.codecraftershub.telemedicine.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse<T>{
    private int status;
    private String message;
    private T data;
}
