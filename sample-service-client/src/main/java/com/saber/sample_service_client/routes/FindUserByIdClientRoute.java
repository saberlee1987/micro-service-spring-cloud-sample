package com.saber.sample_service_client.routes;

import com.saber.sample_service_client.dto.UserDto;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdClientRoute extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE))
                .routeId(Routes.FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE)
                .routeGroup(Routes.FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE_GROUP)
                .log("Request for find user by id ${in.header.id} from consul ===> {{service.provider.baseUrl}}{{service.provider.findUserById}}?id=${in.header.id}")
                .setHeader(Exchange.HTTP_METHOD,constant("GET"))
                .setHeader(Exchange.HTTP_QUERY,simple("id=${in.header.id}"))
                .serviceCall("{{service.provider.name}}/{{service.provider.findUserById}}?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .log("Response for find user by id ${in.header.id} ====> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, UserDto.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
