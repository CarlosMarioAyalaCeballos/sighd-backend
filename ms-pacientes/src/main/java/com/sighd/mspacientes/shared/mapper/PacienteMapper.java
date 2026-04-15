package com.sighd.mspacientes.shared.mapper;

import com.sighd.mspacientes.domain.model.Paciente;
import com.sighd.mspacientes.interfaces.rest.dto.PacienteDtos;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDtos.PacienteResponse toResponse(Paciente paciente);
    Paciente toEntity(PacienteDtos.PacienteRequest request);
    void update(@MappingTarget Paciente paciente, PacienteDtos.PacienteRequest request);
}

