package com.sighd.mstriaje.shared.mapper;

import com.sighd.mstriaje.domain.model.Triaje;
import com.sighd.mstriaje.interfaces.rest.dto.TriajeDtos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TriajeMapper {
    TriajeDtos.TriajeResponse toResponse(Triaje triaje);
}

