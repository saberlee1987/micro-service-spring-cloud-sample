package com.saber.sample_service_provider.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private List<UserDto> users;
}
