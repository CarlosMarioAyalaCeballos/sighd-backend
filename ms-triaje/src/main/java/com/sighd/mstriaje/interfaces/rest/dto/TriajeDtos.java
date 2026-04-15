package com.sighd.mstriaje.interfaces.rest.dto;

import com.sighd.mstriaje.domain.enums.NivelPrioridad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class TriajeDtos {

    public record SignosRequest(
            @NotNull Long pacienteId,
            @NotNull Integer frecuenciaCardiaca,
            @NotBlank String presionArterial,
            @NotNull Double temperatura,
            @NotNull Integer saturacion
    ) {
    }

    public record SintomaRequest(@NotNull Long pacienteId, @NotBlank String descripcion) {
    }

    public record ClasificarRequest(
            @NotNull Long pacienteId,
            @NotNull Integer frecuenciaCardiaca,
            @NotNull Double temperatura,
            @NotNull Integer saturacion,
            List<String> sintomas
    ) {
    }

    public record TriajeResponse(Long id, Long pacienteId, NivelPrioridad nivelPrioridad, LocalDateTime fechaHora, Boolean activo) {
    }
}

