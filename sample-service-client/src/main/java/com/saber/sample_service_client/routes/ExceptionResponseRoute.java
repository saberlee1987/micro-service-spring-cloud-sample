package com.saber.sample_service_client.routes;

import com.saber.sample_service_client.dto.ErrorResponseDto;
import com.saber.sample_service_client.dto.ServiceErrorResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExceptionResponseRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from(String.format("direct:%s", Routes.JSON_EXCEPTION_ROUTE))
                .routeId(Routes.JSON_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(406))
                .process(exchange -> {
                    Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    ErrorResponseDto errorResponseDto = new ErrorResponseDto();
                    errorResponseDto.setCode(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getCode());
                    errorResponseDto.setMessage(ServiceErrorResponseEnum.JSON_ERROR_EXCEPTION.getMessage());
                    errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}", HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage()));
                    log.error("Error for JSON_EXCEPTION_ROUTE ==> {}", errorResponseDto);
                    exchange.getIn().setBody(errorResponseDto);
                });

        from(String.format("direct:%s", Routes.TIMEOUT_EXCEPTION_ROUTE))
                .routeId(Routes.TIMEOUT_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_ROUTE_GROUP)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(504))
                .process(exchange -> {
                    Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    ErrorResponseDto errorResponseDto = new ErrorResponseDto();
                    errorResponseDto.setCode(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getCode());
                    errorResponseDto.setMessage(ServiceErrorResponseEnum.TIMEOUT_EXCEPTION.getMessage());
                    errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}", HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage()));
                    log.error("Error for TIMEOUT_EXCEPTION_ROUTE ==> {}", errorResponseDto);
                    exchange.getIn().setBody(errorResponseDto);
                });

        from(String.format("direct:%s", Routes.HTTP_OPERATION_EXCEPTION_ROUTE))
                .routeId(Routes.HTTP_OPERATION_EXCEPTION_ROUTE)
                .routeGroup(Routes.EXCEPTION_ROUTE_GROUP)
                .process(exchange -> {
                    HttpOperationFailedException exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
                    ErrorResponseDto errorResponseDto = new ErrorResponseDto();
                    errorResponseDto.setCode(ServiceErrorResponseEnum.SERVICE_PROVIDER_EXCEPTION.getCode());
                    errorResponseDto.setMessage(ServiceErrorResponseEnum.SERVICE_PROVIDER_EXCEPTION.getMessage());
                    int statusCode = exception.getStatusCode();
                    String responseBody = exception.getResponseBody();

                    if (responseBody != null && !responseBody.trim().equals("") && responseBody.trim().startsWith("{")) {
                        errorResponseDto.setOriginalMessage(responseBody);
                    } else {
                        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}", HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage()));
                    }
                    log.error("Error for HTTP_OPERATION_EXCEPTION_ROUTE with statusCode {} ==> {}", statusCode, errorResponseDto);
                    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, statusCode);
                    exchange.getIn().setBody(errorResponseDto);
                });
    }
}
