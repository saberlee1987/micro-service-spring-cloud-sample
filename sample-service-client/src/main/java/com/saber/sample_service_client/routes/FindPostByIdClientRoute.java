package com.saber.sample_service_client.routes;

import com.saber.sample_service_client.dto.PostDto;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FindPostByIdClientRoute extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE))
                .routeId(Routes.FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE)
                .routeGroup(Routes.FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE_GROUP)
                .log("Request for find Post by id ${in.header.id} from consul ===> {{service.provider.baseUrl}}{{service.provider.findPostById}}?id=${in.header.id}")
                .setHeader(Exchange.HTTP_METHOD,constant("GET"))
                .setHeader(Exchange.HTTP_QUERY,simple("id=${in.header.id}"))
                .serviceCall("{{service.provider.name}}/{{service.provider.findPostById}}?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .log("Response for find Post by id ${in.header.id} ====> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, PostDto.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
