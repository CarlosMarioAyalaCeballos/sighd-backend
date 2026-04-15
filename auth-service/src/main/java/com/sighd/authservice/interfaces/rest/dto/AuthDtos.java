package com.sighd.authservice.interfaces.rest.dto;

import com.sighd.authservice.domain.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthDtos {

    public record LoginRequest(
            @NotBlank @Size(min = 3, max = 80) String username,
            @NotBlank @Size(min = 6, max = 120) String password
    ) {
    }

    public record LoginResponse(String token, String tipo) {
    }

    public record ValidarTokenRequest(@NotBlank String token) {
    }

    public record ValidarTokenResponse(boolean valido, String username, String rol) {
    }

    public record MeResponse(Long id, String username, Rol rol, Long pacienteId, Boolean activo) {
    }

    public record UsuarioRequest(
            @NotBlank @Size(min = 3, max = 80) String username,
            @NotBlank @Size(min = 6, max = 120) String password,
            @NotNull Rol rol,
            Long pacienteId,
            @NotNull Boolean activo
    ) {
    }

    public record UsuarioUpdateRequest(
            @NotBlank @Size(min = 3, max = 80) String username,
            @NotNull Rol rol,
            Long pacienteId,
            @NotNull Boolean activo
    ) {
    }

    public record UsuarioResponse(Long id, String username, Rol rol, Long pacienteId, Boolean activo) {
    }
}

