package com.saber.sample_service_client.advice;

import com.saber.sample_service_client.dto.ErrorResponseDto;
import com.saber.sample_service_client.dto.ServiceErrorResponseEnum;
import com.saber.sample_service_client.exceptions.ServiceCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SampleAdvice {

    @ExceptionHandler(value = ServiceCallException.class)
    public ResponseEntity<ErrorResponseDto> handleServiceCallException(ServiceCallException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = ex.getStatusCode();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.SERVICE_PROVIDER_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.SERVICE_PROVIDER_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(responseBodyAsString);
        log.error("Error for handleServiceCallException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }

}