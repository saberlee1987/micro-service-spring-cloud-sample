package com.saber.sample_service_provider.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDto> posts;
}
