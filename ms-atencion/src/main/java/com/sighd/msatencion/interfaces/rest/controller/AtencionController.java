package com.sighd.msatencion.interfaces.rest.controller;

import com.sighd.msatencion.application.service.AtencionService;
import com.sighd.msatencion.interfaces.rest.dto.AtencionDtos;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atencion")
public class AtencionController {

    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    @GetMapping("/pacientes-priorizados")
    @PreAuthorize("hasAnyRole('MEDICO','ENFERMERO','ADMIN')")
    public Object pacientesPriorizados() {
        return atencionService.pacientesPriorizados();
    }

    @PostMapping("/atender")
    @PreAuthorize("hasRole('MEDICO')")
    public Object atender(@Valid @RequestBody AtencionDtos.AtenderRequest request, JwtAuthenticationToken jwt) {
        return atencionService.atender(request, jwt);
    }

    @PutMapping("/finalizar/{atencionId}")
    @PreAuthorize("hasRole('MEDICO')")
    public Object finalizar(@PathVariable Long atencionId) {
        return atencionService.finalizar(atencionId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Object findAll() {
        return atencionService.findAll();
    }
}

