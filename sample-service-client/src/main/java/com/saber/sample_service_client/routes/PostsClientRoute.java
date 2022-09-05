package com.saber.sample_service_client.routes;

import com.saber.sample_service_client.dto.PostResponse;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PostsClientRoute extends AbstractRestRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.POSTS_CLIENT_SERVICE_CALL_ROUTE))
                .routeId(Routes.POSTS_CLIENT_SERVICE_CALL_ROUTE)
                .routeGroup(Routes.POSTS_CLIENT_SERVICE_CALL_ROUTE_GROUP)
                .log("Request for call posts from consul ===> {{service.provider.baseUrl}}{{service.provider.posts}}?page=${in.header.page}")
                .setHeader(Exchange.HTTP_METHOD,constant("GET"))
                .setHeader(Exchange.HTTP_QUERY,simple("page=${in.header.page}"))
                .serviceCall("{{service.provider.name}}/{{service.provider.posts}}?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .log("Response for posts ====> ${in.body}")
                .unmarshal().json(JsonLibrary.Jackson, PostResponse.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
