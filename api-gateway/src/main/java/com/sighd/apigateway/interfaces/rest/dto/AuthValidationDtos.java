package com.sighd.apigateway.interfaces.rest.dto;

public class AuthValidationDtos {

    public record ValidarTokenRequest(String token) {
    }

    public record ValidarTokenResponse(boolean valido, String username, String rol) {
    }
}

