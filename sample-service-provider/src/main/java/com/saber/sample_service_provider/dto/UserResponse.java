package com.saber.sample_service_provider.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private List<UserDto> users;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, UserResponse.class);
    }
}
