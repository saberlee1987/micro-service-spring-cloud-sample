package com.saber.sample_service_client.services;

import com.saber.sample_service_client.dto.PostDto;
import com.saber.sample_service_client.dto.PostResponse;
import com.saber.sample_service_client.dto.UserDto;
import com.saber.sample_service_client.dto.UserResponse;

public interface GoRestClientService {
    UserResponse findAllUsers(Integer page);
    UserDto findUserById(Long id);
    PostResponse findAllPosts(Integer page);
    PostDto findPostsById(Long id);
}
