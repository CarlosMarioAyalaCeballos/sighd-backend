package com.sighd.authservice.application.service;

import com.sighd.authservice.domain.model.Usuario;
import com.sighd.authservice.infrastructure.repository.UsuarioRepository;
import com.sighd.authservice.interfaces.rest.dto.AuthDtos;
import com.sighd.authservice.shared.exception.NotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtService jwtService,
                       UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthDtos.LoginResponse login(AuthDtos.LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsernameAndActivoTrue(request.username())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new BadCredentialsException("Credenciales invalidas");
        }


        return new AuthDtos.LoginResponse(jwtService.generarToken(usuario), "Bearer");
    }

    public AuthDtos.ValidarTokenResponse validar(String token) {
        Jwt jwt = jwtService.validar(token);
        return new AuthDtos.ValidarTokenResponse(true, jwt.getSubject(), jwt.getClaimAsString("rol"));
    }

    public AuthDtos.MeResponse me(String username) {
        Usuario usuario = usuarioRepository.findByUsernameAndActivoTrue(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return new AuthDtos.MeResponse(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol(),
                usuario.getPacienteId(),
                usuario.getActivo()
        );
    }
}

