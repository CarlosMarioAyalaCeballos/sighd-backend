package com.sighd.authservice.application.service;

import com.sighd.authservice.domain.model.Usuario;
import com.sighd.authservice.infrastructure.repository.UsuarioRepository;
import com.sighd.authservice.interfaces.rest.dto.AuthDtos;
import com.sighd.authservice.shared.exception.NotFoundException;
import com.sighd.authservice.shared.mapper.UsuarioMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AuthDtos.UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toResponse).toList();
    }

    public AuthDtos.UsuarioResponse create(AuthDtos.UsuarioRequest request) {
        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setActivo(request.activo());
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    public AuthDtos.UsuarioResponse update(Long id, AuthDtos.UsuarioUpdateRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no existe"));

        usuario.setUsername(request.username());
        usuario.setRol(request.rol());
        usuario.setPacienteId(request.pacienteId());
        usuario.setActivo(request.activo());

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }
}

