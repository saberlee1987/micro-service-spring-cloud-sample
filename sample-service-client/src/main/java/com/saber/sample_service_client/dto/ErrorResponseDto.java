package com.saber.sample_service_client.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

import java.util.List;

@Data
public class ErrorResponseDto {
    private Integer code;
    private String message;
    @JsonRawValue
    private Object originalMessage;
    private List<ValidationDto> validationDetails;
    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, ErrorResponseDto.class);
    }
}
