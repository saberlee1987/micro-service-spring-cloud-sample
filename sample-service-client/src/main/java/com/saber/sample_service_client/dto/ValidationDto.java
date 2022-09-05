package com.saber.sample_service_client.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDto {
    private String fieldName;
    private String constraintMessage;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, ValidationDto.class);
    }
}
