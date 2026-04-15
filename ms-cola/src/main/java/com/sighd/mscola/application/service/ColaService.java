package com.sighd.mscola.application.service;

import com.sighd.mscola.domain.enums.EstadoCola;
import com.sighd.mscola.domain.model.ColaAtencion;
import com.sighd.mscola.infrastructure.repository.ColaAtencionRepository;
import com.sighd.mscola.interfaces.rest.dto.ColaDtos;
import com.sighd.mscola.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ColaService {

    private final ColaAtencionRepository colaAtencionRepository;

    public ColaService(ColaAtencionRepository colaAtencionRepository) {
        this.colaAtencionRepository = colaAtencionRepository;
    }

    public ColaAtencion agregar(ColaDtos.AgregarRequest request) {
        ColaAtencion cola = new ColaAtencion();
        cola.setPacienteId(request.pacienteId());
        cola.setNivelPrioridad(request.nivelPrioridad());
        cola.setEstado(EstadoCola.EN_ESPERA);
        return colaAtencionRepository.save(cola);
    }

    public List<ColaAtencion> listarEnEspera() {
        return colaAtencionRepository.findByEstadoOrderByNivelPrioridadAscFechaIngresoAsc(EstadoCola.EN_ESPERA);
    }

    public ColaAtencion cambiarEstado(Long id, ColaDtos.EstadoRequest request) {
        ColaAtencion cola = colaAtencionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro de cola no existe"));

        cola.setEstado(request.estado());
        if (request.estado() == EstadoCola.EN_ATENCION && cola.getFechaInicioAtencion() == null) {
            cola.setFechaInicioAtencion(LocalDateTime.now());
        }
        if (request.estado() == EstadoCola.FINALIZADO) {
            cola.setFechaFin(LocalDateTime.now());
        }
        return colaAtencionRepository.save(cola);
    }

    public ColaAtencion findById(Long id) {
        return colaAtencionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro de cola no existe"));
    }
}

