package com.saber.sample_service_client.routes;

public interface Routes {
    String USERS_CLIENT_SERVICE_CALL_ROUTE = "users_client_service_call_route";
    String USERS_CLIENT_SERVICE_CALL_ROUTE_GROUP = "users_client_service_call_route_group";

    String FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE = "find_user_by_id_client_service_call_route";
    String FIND_USER_BY_ID_CLIENT_SERVICE_CALL_ROUTE_GROUP = "find_user_by_id_client_service_call_route_group";

    String FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE = "find_post_by_id_client_service_call_route";
    String FIND_POST_BY_ID_CLIENT_SERVICE_CALL_ROUTE_GROUP = "find_post_by_id_client_service_call_route_group";

    String POSTS_CLIENT_SERVICE_CALL_ROUTE = "posts_client_service_call_route";
    String POSTS_CLIENT_SERVICE_CALL_ROUTE_GROUP = "posts_client_service_call_route_group";

    String CONSUL_REGISTRATION_ROUTE = "consul-registration-route";
    String CONSUL_REGISTRATION_ROUTE_GROUP = "consul-registration-route-group";


    String TIMEOUT_EXCEPTION_ROUTE = "timeout-exception-route";
    String JSON_EXCEPTION_ROUTE = "json-exception-route";
    String HTTP_OPERATION_EXCEPTION_ROUTE = "http-operation-exception-route";
    String EXCEPTION_ROUTE_GROUP = "exception-route-group";
}
