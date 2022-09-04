package com.saber.sample_service_provider.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
