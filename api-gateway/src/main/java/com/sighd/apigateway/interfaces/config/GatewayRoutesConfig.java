package com.sighd.apigateway.interfaces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    RouterFunction<ServerResponse> authRoute() {
        return route("auth-service")
                .route(path("/auth/**"), http())
                .before(uri("${routes.auth-service}"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> pacientesRoute() {
        return route("ms-pacientes")
                .route(path("/api/pacientes/**"), http())
                .before(uri("${routes.ms-pacientes}"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> triajeRoute() {
        return route("ms-triaje")
                .route(path("/api/triaje/**"), http())
                .before(uri("${routes.ms-triaje}"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> colaRoute() {
        return route("ms-cola")
                .route(path("/api/cola/**"), http())
                .before(uri("${routes.ms-cola}"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> atencionRoute() {
        return route("ms-atencion")
                .route(path("/api/atencion/**"), http())
                .before(uri("${routes.ms-atencion}"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> adminRoute() {
        return route("ms-admin")
                .route(path("/api/admin/**"), http())
                .before(uri("${routes.ms-admin}"))
                .build();
    }
}
