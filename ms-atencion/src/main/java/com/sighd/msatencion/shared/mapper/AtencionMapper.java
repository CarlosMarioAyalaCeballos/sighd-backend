package com.sighd.msatencion.shared.mapper;

import com.sighd.msatencion.domain.model.AtencionMedica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtencionMapper {
    AtencionMedica clone(AtencionMedica source);
}

