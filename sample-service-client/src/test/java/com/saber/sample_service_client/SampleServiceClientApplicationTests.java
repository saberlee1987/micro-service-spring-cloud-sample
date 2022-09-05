package com.saber.sample_service_client;

import com.saber.sample_service_client.dto.UserResponse;
import com.saber.sample_service_client.routes.Routes;
import com.saber.sample_service_client.services.GoRestClientService;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleServiceClientApplicationTests {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private GoRestClientService goRestClientService;

    @Test
    void test1() {
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.USERS_CLIENT_SERVICE_CALL_ROUTE), exchange -> {

        });
        Integer statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        String body = responseExchange.getIn().getBody(String.class);

        System.out.println("statusCode ===> " + statusCode);
        System.out.println("response body ===> " + body);
    }
    @Test
    void test2(){
        UserResponse allUsers = goRestClientService.findAllUsers(14);
        System.out.println(allUsers);
    }
    @Test
    void test3(){
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE), exchange -> {

            exchange.getIn().setHeader("id",18);
        });
        Integer statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        String body = responseExchange.getIn().getBody(String.class);

        System.out.println("statusCode ===> " + statusCode);
        System.out.println("response body ===> " + body);
    }

    @Test
    void test4(){
        Exchange responseExchange = producerTemplate.send(String.format("direct:%s", Routes.FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE), exchange -> {

            exchange.getIn().setHeader("id",27);
        });
        Integer statusCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
        String body = responseExchange.getIn().getBody(String.class);

        System.out.println("statusCode ===> " + statusCode);
        System.out.println("response body ===> " + body);
    }

}
