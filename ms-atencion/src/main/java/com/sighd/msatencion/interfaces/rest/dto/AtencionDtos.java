package com.sighd.msatencion.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtencionDtos {

    public record AtenderRequest(
            @NotNull Long pacienteId,
            @NotNull Long colaId,
            @NotBlank @Size(max = 1000) String diagnostico,
            @NotBlank @Size(max = 1000) String tratamiento,
            @NotNull Boolean requiereHospitalizacion
    ) {
    }
}

