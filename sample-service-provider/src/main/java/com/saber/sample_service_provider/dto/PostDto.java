package com.saber.sample_service_provider.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private Long user_id;
    private String title;
    private String body;
}
