package com.sighd.apigateway.interfaces.config;

import com.sighd.apigateway.interfaces.rest.dto.AuthValidationDtos;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

    private final RestClient restClient;

    public JwtValidationFilter(@Value("${auth-service.base-url}") String authServiceBaseUrl) {
        this.restClient = RestClient.builder().baseUrl(authServiceBaseUrl).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/auth/login") || path.startsWith("/auth/validar")) {
            filterChain.doFilter(request, response);
            return;
        }

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (auth == null || !auth.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            AuthValidationDtos.ValidarTokenResponse res = restClient.post()
                    .uri("/auth/validar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new AuthValidationDtos.ValidarTokenRequest(auth.substring(7)))
                    .retrieve()
                    .body(AuthValidationDtos.ValidarTokenResponse.class);

            if (res == null || !res.valido()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}

