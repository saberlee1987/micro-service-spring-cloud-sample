package com.saber.sample_service_provider.services.impl;

import com.saber.sample_service_provider.dto.PostDto;
import com.saber.sample_service_provider.dto.PostResponse;
import com.saber.sample_service_provider.dto.UserDto;
import com.saber.sample_service_provider.dto.UserResponse;
import com.saber.sample_service_provider.services.GoRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoRestServiceImpl implements GoRestService {

    private final RestTemplate restTemplate;
    @Value(value = "${service.goRest.baseUrl}")
    private String goRestBaseUrl;
    @Value(value = "${service.goRest.users}")
    private String usersUrl;
    @Value(value = "${service.goRest.posts}")
    private String postsUrl;
    @Value(value = "${service.goRest.token}")
    private String token;

    @Override
    public UserResponse findAllUsers(Integer page) {
        String url = String.format("%s%s?page=%s", goRestBaseUrl, usersUrl, page);
        log.info("Request for findAllUsers with page {} to url {}", page, url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<UserDto>> parameterizedTypeReference = new ParameterizedTypeReference<List<UserDto>>() {
        };
        ResponseEntity<List<UserDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, parameterizedTypeReference);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        List<UserDto> body = responseEntity.getBody();
        UserResponse response = new UserResponse();
        response.setUsers(body);
        log.info("Response for findAllUsers with page {} with statusCode {} ===> {}", page, statusCodeValue, response);

        return response;
    }

    @Override
    public UserDto findUserById(Long id) {
        String url = String.format("%s%s/%s", goRestBaseUrl, usersUrl, id);
        log.info("Request for findUserById with id {} to url {}", id, url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<UserDto> parameterizedTypeReference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, parameterizedTypeReference);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        UserDto body = responseEntity.getBody();
        log.info("Response for findUserById with id {} with statusCode {} ===> {}", id, statusCodeValue, body);
        return body;
    }

    @Override
    public PostResponse findAllPosts(Integer page) {
        String url = String.format("%s%s?page=%s", goRestBaseUrl, postsUrl, page);
        log.info("Request for findAllPosts with page {} to url {}", page, url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<PostDto>> parameterizedTypeReference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PostDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, parameterizedTypeReference);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        List<PostDto> body = responseEntity.getBody();
        PostResponse response = new PostResponse();
        response.setPosts(body);
        log.info("Response for findAllPosts with page {} with statusCode {} ===> {}", page, statusCodeValue, response);

        return response;
    }

    @Override
    public PostDto findPostsById(Long id) {
        String url = String.format("%s%s/%s", goRestBaseUrl, postsUrl, id);
        log.info("Request for findPostsById with id {} to url {}", id, url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<PostDto> parameterizedTypeReference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, parameterizedTypeReference);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        PostDto body = responseEntity.getBody();
        log.info("Response for findPostsById with id {} with statusCode {} ===> {}", id, statusCodeValue, body);
        return body;
    }
}
