package com.sighd.mstriaje.interfaces.rest.controller;

import com.sighd.mstriaje.application.service.TriajeService;
import com.sighd.mstriaje.interfaces.rest.dto.TriajeDtos;
import com.sighd.mstriaje.shared.mapper.TriajeMapper;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/triaje")
public class TriajeController {

    private final TriajeService triajeService;
    private final TriajeMapper triajeMapper;

    public TriajeController(TriajeService triajeService, TriajeMapper triajeMapper) {
        this.triajeService = triajeService;
        this.triajeMapper = triajeMapper;
    }

    @PostMapping("/signos")
    @PreAuthorize("hasAnyRole('ENFERMERO','MEDICO')")
    public Object guardarSignos(@Valid @RequestBody TriajeDtos.SignosRequest request) {
        return triajeService.guardarSignos(request);
    }

    @PostMapping("/sintomas")
    @PreAuthorize("hasAnyRole('ENFERMERO','MEDICO')")
    public Object guardarSintoma(@Valid @RequestBody TriajeDtos.SintomaRequest request) {
        return triajeService.guardarSintoma(request);
    }

    @PostMapping("/clasificar")
    @PreAuthorize("hasAnyRole('ENFERMERO','MEDICO')")
    public TriajeDtos.TriajeResponse clasificar(@Valid @RequestBody TriajeDtos.ClasificarRequest request) {
        return triajeMapper.toResponse(triajeService.clasificar(request));
    }

    @GetMapping("/historial/{pacienteId}")
    @PreAuthorize("isAuthenticated()")
    public Object historial(@PathVariable Long pacienteId) {
        return triajeService.historial(pacienteId).stream().map(triajeMapper::toResponse).toList();
    }
}

