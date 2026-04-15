package com.sighd.mspacientes.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PacienteDtos {

    public record PacienteRequest(
            @NotBlank @Size(max = 120) String nombres,
            @NotBlank @Size(max = 120) String apellidos,
            @NotBlank @Size(max = 30) String documento,
            @NotNull LocalDate fechaNacimiento,
            @NotBlank @Size(max = 20) String genero,
            @Size(max = 30) String telefono,
            @Size(max = 200) String direccion
    ) {
    }

    public record PacienteResponse(
            Long id,
            String nombres,
            String apellidos,
            String documento,
            LocalDate fechaNacimiento,
            String genero,
            String telefono,
            String direccion
    ) {
    }
}

