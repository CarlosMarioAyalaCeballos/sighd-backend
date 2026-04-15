package com.sighd.mscola.interfaces.rest.controller;

import com.sighd.mscola.application.service.ColaService;
import com.sighd.mscola.interfaces.rest.dto.ColaDtos;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cola")
public class ColaController {

    private final ColaService colaService;

    public ColaController(ColaService colaService) {
        this.colaService = colaService;
    }

    @PostMapping("/agregar")
    @PreAuthorize("hasAnyRole('ENFERMERO','MEDICO','ADMIN')")
    public Object agregar(@Valid @RequestBody ColaDtos.AgregarRequest request) {
        return colaService.agregar(request);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Object listar() {
        return colaService.listarEnEspera();
    }

    @PatchMapping("/{colaId}/estado")
    @PreAuthorize("hasAnyRole('MEDICO','ADMIN')")
    public Object cambiarEstado(@PathVariable Long colaId, @Valid @RequestBody ColaDtos.EstadoRequest request) {
        return colaService.cambiarEstado(colaId, request);
    }

    @GetMapping("/{colaId}")
    @PreAuthorize("isAuthenticated()")
    public Object findById(@PathVariable Long colaId) {
        return colaService.findById(colaId);
    }
}

