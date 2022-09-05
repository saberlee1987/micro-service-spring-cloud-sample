package com.saber.sample_service_provider.dto;

import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> posts;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                .enableComplexMapKeySerialization()
                .create().toJson(this, PostResponse.class);
    }
}
