package com.saber.sample_service_provider.advice;

import com.saber.sample_service_provider.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class SampleAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> handleHttpClientErrorException(HttpClientErrorException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = ex.getStatusCode().value();
        String responseBodyAsString = ex.getResponseBodyAsString();
        errorResponseDto.setCode(statusCode);
        errorResponseDto.setMessage(ex.getStatusText());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));

        log.error("Error for handleHttpClientErrorException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }
}
