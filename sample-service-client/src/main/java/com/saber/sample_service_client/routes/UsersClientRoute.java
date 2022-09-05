package com.saber.sample_service_client.routes;

import com.saber.sample_service_client.dto.UserResponse;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class UsersClientRoute extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.USERS_CLIENT_SERVICE_CALL_ROUTE))
                .routeId(Routes.USERS_CLIENT_SERVICE_CALL_ROUTE)
                .routeGroup(Routes.USERS_CLIENT_SERVICE_CALL_ROUTE_GROUP)
                .log("Request for call users from consul ===> {{service.provider.baseUrl}}{{service.provider.users}}?page=${in.header.page}")
                .setHeader(Exchange.HTTP_METHOD,constant("GET"))
                .setHeader(Exchange.HTTP_QUERY,simple("page=${in.header.page}"))
                .serviceCall("{{service.provider.name}}/{{service.provider.users}}?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .log("Response for users ====> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, UserResponse.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
