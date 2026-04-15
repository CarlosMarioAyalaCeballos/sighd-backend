package com.sighd.apigateway.interfaces.config;

import org.springframework.beans.factory.annotation.Value;
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
    RouterFunction<ServerResponse> authRoute(@Value("${routes.auth-service}") String routeUrl) {
        return route("auth-service")
                .route(path("/auth/**"), http())
                .before(uri(routeUrl))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> pacientesRoute(@Value("${routes.ms-pacientes}") String routeUrl) {
        return route("ms-pacientes")
                .route(path("/api/pacientes/**"), http())
                .before(uri(routeUrl))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> triajeRoute(@Value("${routes.ms-triaje}") String routeUrl) {
        return route("ms-triaje")
                .route(path("/api/triaje/**"), http())
                .before(uri(routeUrl))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> colaRoute(@Value("${routes.ms-cola}") String routeUrl) {
        return route("ms-cola")
                .route(path("/api/cola/**"), http())
                .before(uri(routeUrl))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> atencionRoute(@Value("${routes.ms-atencion}") String routeUrl) {
        return route("ms-atencion")
                .route(path("/api/atencion/**"), http())
                .before(uri(routeUrl))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> adminRoute(@Value("${routes.ms-admin}") String routeUrl) {
        return route("ms-admin")
                .route(path("/api/admin/**"), http())
                .before(uri(routeUrl))
                .build();
    }
}
