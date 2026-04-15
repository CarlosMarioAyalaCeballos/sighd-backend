package com.sighd.msadmin.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AdminDtos {

    public record UsuarioCreateRequest(
            @NotBlank String username,
            @NotBlank String password,
            @NotBlank String rol,
            Long pacienteId,
            @NotNull Boolean activo
    ) {
    }

    public record UsuarioUpdateRequest(
            @NotBlank String username,
            @NotBlank String rol,
            Long pacienteId,
            @NotNull Boolean activo
    ) {
    }

    public record ReglaRequest(@NotBlank String nombre, @NotBlank String condicion, @NotBlank String nivelAsignado) {
    }

    public record ReglaBatchRequest(@NotNull List<ReglaRequest> reglas) {
    }
}

