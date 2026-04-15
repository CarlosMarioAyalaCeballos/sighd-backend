package com.sighd.authservice.shared.mapper;

import com.sighd.authservice.domain.model.Usuario;
import com.sighd.authservice.interfaces.rest.dto.AuthDtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    AuthDtos.UsuarioResponse toResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    Usuario toEntity(AuthDtos.UsuarioRequest request);
}

