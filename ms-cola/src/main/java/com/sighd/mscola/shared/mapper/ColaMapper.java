package com.sighd.mscola.shared.mapper;

import com.sighd.mscola.domain.model.ColaAtencion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaMapper {
    ColaAtencion clone(ColaAtencion source);
}

