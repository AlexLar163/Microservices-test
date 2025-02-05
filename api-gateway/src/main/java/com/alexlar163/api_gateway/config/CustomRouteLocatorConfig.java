package com.alexlar163.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteLocatorConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // adding 2 rotes to first microservice as we need to log request body if method is POST
        return builder.routes()
                .route("authentication-server",r -> r.path("/auth/**")
                        .uri("http://localhost:8090"))
                .route("customer-service",r -> r.path("/customer/**")
                        .uri("http://localhost:8091"))
                .route("account-service",r -> r.path("/account/**")
                        .uri("http://localhost:8092"))
                .build();
    }


}
