package com.sighd.authservice.interfaces.rest.controller;

import com.sighd.authservice.application.service.AuthService;
import com.sighd.authservice.interfaces.rest.dto.AuthDtos;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDtos.LoginResponse login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/validar")
    public AuthDtos.ValidarTokenResponse validar(@Valid @RequestBody AuthDtos.ValidarTokenRequest request) {
        return authService.validar(request.token());
    }

    @GetMapping("/me")
    public AuthDtos.MeResponse me(Authentication authentication) {
        return authService.me(authentication.getName());
    }
}

