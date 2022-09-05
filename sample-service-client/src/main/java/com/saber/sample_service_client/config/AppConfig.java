package com.saber.sample_service_client.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
                .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false)
                ;
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder,
                                     @Value("${service.restTemplate.connectionTimeToLive}") long connectionTimeToLive ,
                                     @Value("${service.restTemplate.connectionRequestTimeout}") int connectionRequestTimeout ,
                                     @Value("${service.restTemplate.socketTimeout}") int socketTimeout ,
                                     @Value("${service.restTemplate.connectionTimeout}") int connectionTimeout ,
                                     @Value("${service.restTemplate.maxTotalConnection}") int maxTotalConnection ,
                                     @Value("${service.restTemplate.connectionPerRoute}") int connectionPerRoute
    ){

        HttpClient httpClient= HttpClientBuilder
                .create()
                .setConnectionTimeToLive(connectionTimeToLive, TimeUnit.MILLISECONDS)
                .setMaxConnPerRoute(connectionPerRoute)
                .setMaxConnTotal(maxTotalConnection)
                   .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(socketTimeout);
        requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
               RestTemplate restTemplate =builder
                .build();
       restTemplate.setRequestFactory(requestFactory);
       return restTemplate;
    }
}
