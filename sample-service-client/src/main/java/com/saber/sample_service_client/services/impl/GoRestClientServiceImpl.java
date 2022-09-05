package com.saber.sample_service_client.services.impl;

import com.saber.sample_service_client.dto.*;
import com.saber.sample_service_client.exceptions.ServiceCallException;
import com.saber.sample_service_client.routes.Routes;
import com.saber.sample_service_client.services.GoRestClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoRestClientServiceImpl implements GoRestClientService {

    private final RestTemplate restTemplate;

    @Value(value = "${service.provider.baseUrl}")
    private String baseUrl;
    @Value(value = "${service.provider.users}")
    private String usersClientUrl;
    @Value(value = "${service.provider.posts}")
    private String postsClientUrl;
    @Value(value = "${service.provider.findUserById}")
    private String findUserById;
    @Value(value = "${service.provider.findPostById}")
    private String findPostById;

    @Autowired
    private ProducerTemplate producerTemplate;


    @Override
    public UserResponse findAllUsers(Integer page) {
        UserResponse body;
        int statusCode;
        try {
            String url = String.format("%s%s?page=%s", baseUrl, usersClientUrl, page);
            ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, UserResponse.class);
            statusCode = responseEntity.getStatusCodeValue();
            body = responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Error for call service all users from eureka ====> {} ", ex.getMessage());
            Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.USERS_CLIENT_SERVICE_CALL_ROUTE), exchange -> {
                exchange.getIn().setHeader("page", page);
            });
            statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

            checkServiceCallException(statusCode, responseExchange);

            body = responseExchange.getIn().getBody(UserResponse.class);
        }
        log.info("Response for all users with statusCode {} ====> {}", statusCode, body);
        return body;
    }

    @Override
    public UserDto findUserById(Long id) {
        int statusCode;
        UserDto body;
        try {
            String url = String.format("%s%s/%s", baseUrl, findUserById, id);
            ResponseEntity<UserDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
            statusCode = responseEntity.getStatusCodeValue();
            body = responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Error for call service findUserById from eureka ====> {} ", ex.getMessage());
            Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE), exchange -> {
                exchange.getIn().setHeader("id", id);
            });
            statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

            checkServiceCallException(statusCode, responseExchange);

            body = responseExchange.getIn().getBody(UserDto.class);
        }
        log.info("Response for findUserById with statusCode {} ====> {}", statusCode, body);
        return body;
    }

    @Override
    public PostResponse findAllPosts(Integer page) {
        int statusCode;
        PostResponse body;
        try {
            String url = String.format("%s%s?page=%s", baseUrl, postsClientUrl, page);
            ResponseEntity<PostResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, PostResponse.class);
            statusCode = responseEntity.getStatusCodeValue();
            body = responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Error for call service all posts from eureka ====> {} ", ex.getMessage());
            Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.POSTS_CLIENT_SERVICE_CALL_ROUTE), exchange -> {
                exchange.getIn().setHeader("page", page);
            });
            statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

            checkServiceCallException(statusCode, responseExchange);

            body = responseExchange.getIn().getBody(PostResponse.class);
        }
        log.info("Response for all posts with statusCode {} ====> {}", statusCode, body);
        return body;
    }

    @Override
    public PostDto findPostsById(Long id) {
        int statusCode;
        PostDto body;
        try {
            String url = String.format("%s%s/%s", baseUrl, findPostById, id);
            ResponseEntity<PostDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, PostDto.class);
            statusCode = responseEntity.getStatusCodeValue();
            body = responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Error for call service findPostsById from eureka ====> {} ", ex.getMessage());
            Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE), exchange -> {
                exchange.getIn().setHeader("id", id);
            });
            statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);

            checkServiceCallException(statusCode, responseExchange);

            body = responseExchange.getIn().getBody(PostDto.class);
        }

        log.info("Response for findPostsById with statusCode {} ====> {}", statusCode, body);
        return body;
    }

    private void checkServiceCallException(int statusCode, Exchange exchange) {
        if (statusCode != HttpStatus.OK.value() && exchange.getIn().getBody() instanceof ErrorResponseDto) {
            ErrorResponseDto errorResponseDto = exchange.getIn().getBody(ErrorResponseDto.class);
            throw new ServiceCallException(errorResponseDto.getOriginalMessage().toString(), statusCode);
        }
    }
}
