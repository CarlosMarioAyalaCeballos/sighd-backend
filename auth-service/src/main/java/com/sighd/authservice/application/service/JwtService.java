package com.sighd.authservice.application.service;

import com.sighd.authservice.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${security.jwt.expiration-seconds:3600}")
    private long expirationSeconds;

    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generarToken(Usuario usuario) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("sighd-auth")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .subject(usuario.getUsername())
                .claim("rol", usuario.getRol().name())
                .claim("usuarioId", usuario.getId())
                .claim("pacienteId", usuario.getPacienteId())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt validar(String rawToken) {
        String token = rawToken != null && rawToken.startsWith("Bearer ") ? rawToken.substring(7) : rawToken;
        return jwtDecoder.decode(token);
    }
}

