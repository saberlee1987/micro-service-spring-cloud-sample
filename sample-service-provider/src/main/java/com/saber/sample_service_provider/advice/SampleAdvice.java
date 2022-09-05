package com.saber.sample_service_provider.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.saber.sample_service_provider.dto.ErrorResponseDto;
import com.saber.sample_service_provider.dto.ServiceErrorResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;
import java.net.SocketException;

@RestControllerAdvice
@Slf4j
public class SampleAdvice {

    @ExceptionHandler(value = HttpClientErrorException.class)
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
    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpServerErrorException(HttpServerErrorException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = ex.getStatusCode().value();
        String responseBodyAsString = ex.getResponseBodyAsString();
        errorResponseDto.setCode(statusCode);
        errorResponseDto.setMessage(ex.getStatusText());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));

        log.error("Error for handleHttpServerErrorException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }
    @ExceptionHandler(value = SocketException.class)
    public ResponseEntity<ErrorResponseDto> handleSocketException(SocketException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = HttpStatus.GATEWAY_TIMEOUT.value();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));
        log.error("Error for SocketException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }
    @ExceptionHandler(value = ConnectException.class)
    public ResponseEntity<ErrorResponseDto> handleConnectException(ConnectException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = HttpStatus.GATEWAY_TIMEOUT.value();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));
        log.error("Error for ConnectException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }

    @ExceptionHandler(value = ConnectTimeoutException.class)
    public ResponseEntity<ErrorResponseDto> handleConnectTimeoutException(ConnectTimeoutException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = HttpStatus.GATEWAY_TIMEOUT.value();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));
        log.error("Error for ConnectTimeoutException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseException(JsonParseException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = HttpStatus.GATEWAY_TIMEOUT.value();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));
        log.error("Error for JsonParseException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }
    @ExceptionHandler(value = JsonMappingException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseException(JsonMappingException ex) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        int statusCode = HttpStatus.GATEWAY_TIMEOUT.value();
        String responseBodyAsString = ex.getMessage();
        errorResponseDto.setCode(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getCode());
        errorResponseDto.setMessage(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"message\":%s}", statusCode, responseBodyAsString));
        log.error("Error for JsonParseException ====> {}", responseBodyAsString);
        return ResponseEntity.status(statusCode).body(errorResponseDto);
    }
}