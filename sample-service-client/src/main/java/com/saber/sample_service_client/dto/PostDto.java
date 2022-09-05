package com.saber.sample_service_client.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private Long user_id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, PostDto.class);
    }
}
