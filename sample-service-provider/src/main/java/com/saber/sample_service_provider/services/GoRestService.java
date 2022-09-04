package com.saber.sample_service_provider.services;

import com.saber.sample_service_provider.dto.PostDto;
import com.saber.sample_service_provider.dto.PostResponse;
import com.saber.sample_service_provider.dto.UserDto;
import com.saber.sample_service_provider.dto.UserResponse;

public interface GoRestService {
    UserResponse findAllUsers(Integer page);
    UserDto findUserById(Long id);
    PostResponse findAllPosts(Integer page);
    PostDto findPostsById(Long id);
}
