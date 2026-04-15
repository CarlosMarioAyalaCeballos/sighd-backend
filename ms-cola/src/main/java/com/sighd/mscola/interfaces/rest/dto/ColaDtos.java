package com.sighd.mscola.interfaces.rest.dto;

import com.sighd.mscola.domain.enums.EstadoCola;
import com.sighd.mscola.domain.enums.NivelPrioridad;
import jakarta.validation.constraints.NotNull;

public class ColaDtos {

    public record AgregarRequest(@NotNull Long pacienteId, @NotNull NivelPrioridad nivelPrioridad) {
    }

    public record EstadoRequest(@NotNull EstadoCola estado) {
    }
}

