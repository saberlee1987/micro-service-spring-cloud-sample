package com.saber.sample_service_client.routes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.http.conn.ConnectTimeoutException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class AbstractRestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(JsonParseException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for JsonParseException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.JSON_EXCEPTION_ROUTE));

        onException(JsonMappingException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for JsonMappingException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.JSON_EXCEPTION_ROUTE));

        onException(SocketException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for SocketException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.TIMEOUT_EXCEPTION_ROUTE));

        onException(SocketTimeoutException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for SocketTimeoutException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.TIMEOUT_EXCEPTION_ROUTE));

        onException(ConnectException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for ConnectException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.TIMEOUT_EXCEPTION_ROUTE));

        onException(ConnectTimeoutException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for ConnectTimeoutException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.TIMEOUT_EXCEPTION_ROUTE));


        onException(HttpOperationFailedException.class)
                .handled(true)
                .maximumRedeliveries(0)
                .log("Error for HttpOperationFailedException with body ===> " + exceptionMessage())
                .to(String.format("direct:%s", Routes.HTTP_OPERATION_EXCEPTION_ROUTE));


    }
}
