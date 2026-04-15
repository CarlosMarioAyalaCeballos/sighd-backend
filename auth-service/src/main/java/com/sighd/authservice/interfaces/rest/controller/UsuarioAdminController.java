package com.sighd.authservice.interfaces.rest.controller;

import com.sighd.authservice.application.service.UsuarioService;
import com.sighd.authservice.interfaces.rest.dto.AuthDtos;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/users")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioAdminController {

    private final UsuarioService usuarioService;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<AuthDtos.UsuarioResponse> list() {
        return usuarioService.findAll();
    }

    @PostMapping
    public AuthDtos.UsuarioResponse create(@Valid @RequestBody AuthDtos.UsuarioRequest request) {
        return usuarioService.create(request);
    }

    @PutMapping("/{id}")
    public AuthDtos.UsuarioResponse update(@PathVariable Long id, @Valid @RequestBody AuthDtos.UsuarioUpdateRequest request) {
        return usuarioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}

