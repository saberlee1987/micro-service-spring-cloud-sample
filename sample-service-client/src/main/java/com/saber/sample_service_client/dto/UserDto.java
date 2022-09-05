package com.saber.sample_service_client.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String status;
    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, UserDto.class);
    }
}
