package com.sighd.msadmin.shared.mapper;

import com.sighd.msadmin.domain.model.ReglaPriorizacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    ReglaPriorizacion clone(ReglaPriorizacion source);
}

