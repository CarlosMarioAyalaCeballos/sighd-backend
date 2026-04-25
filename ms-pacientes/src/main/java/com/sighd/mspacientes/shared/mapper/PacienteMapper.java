package com.sighd.mspacientes.shared.mapper;

import com.sighd.mspacientes.domain.model.Paciente;
import com.sighd.mspacientes.interfaces.rest.dto.PacienteDtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDtos.PacienteResponse toResponse(Paciente paciente);

    @Mapping(target = "id", ignore = true)
    Paciente toEntity(PacienteDtos.PacienteRequest request);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Paciente paciente, PacienteDtos.PacienteRequest request);
}

