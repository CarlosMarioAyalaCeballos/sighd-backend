package com.sighd.mspacientes.interfaces.rest.controller;

import com.sighd.mspacientes.application.service.PacienteService;
import com.sighd.mspacientes.interfaces.rest.dto.PacienteDtos;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('RECEPCIONISTA','ENFERMERO','ADMIN')")
    public PacienteDtos.PacienteResponse create(@Valid @RequestBody PacienteDtos.PacienteRequest request) {
        return pacienteService.create(request);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PacienteDtos.PacienteResponse> findAll() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public PacienteDtos.PacienteResponse findById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('RECEPCIONISTA','ENFERMERO','ADMIN')")
    public PacienteDtos.PacienteResponse update(@PathVariable Long id, @Valid @RequestBody PacienteDtos.PacienteRequest request) {
        return pacienteService.update(id, request);
    }
}

