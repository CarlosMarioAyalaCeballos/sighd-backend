package com.sighd.msatencion.application.service;

import com.sighd.msatencion.domain.model.AtencionMedica;
import com.sighd.msatencion.infrastructure.client.ColaClient;
import com.sighd.msatencion.infrastructure.repository.AtencionMedicaRepository;
import com.sighd.msatencion.interfaces.rest.dto.AtencionDtos;
import com.sighd.msatencion.shared.exception.NotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AtencionService {

    private final ColaClient colaClient;
    private final AtencionMedicaRepository atencionMedicaRepository;

    public AtencionService(ColaClient colaClient, AtencionMedicaRepository atencionMedicaRepository) {
        this.colaClient = colaClient;
        this.atencionMedicaRepository = atencionMedicaRepository;
    }

    public List<Map<String, Object>> pacientesPriorizados() {
        return colaClient.colaPriorizada();
    }

    public AtencionMedica atender(AtencionDtos.AtenderRequest request, JwtAuthenticationToken jwt) {
        Long medicoId = jwt.getToken().getClaim("usuarioId");

        AtencionMedica atencion = new AtencionMedica();
        atencion.setPacienteId(request.pacienteId());
        atencion.setColaId(request.colaId());
        atencion.setMedicoId(medicoId);
        atencion.setDiagnostico(request.diagnostico());
        atencion.setTratamiento(request.tratamiento());
        atencion.setRequiereHospitalizacion(request.requiereHospitalizacion());

        AtencionMedica saved = atencionMedicaRepository.save(atencion);
        colaClient.cambiarEstado(request.colaId(), new ColaClient.EstadoRequest("EN_ATENCION"));
        return saved;
    }

    public AtencionMedica finalizar(Long atencionId) {
        AtencionMedica atencion = atencionMedicaRepository.findById(atencionId)
                .orElseThrow(() -> new NotFoundException("Atencion no existe"));

        atencion.setFinalizada(true);
        AtencionMedica saved = atencionMedicaRepository.save(atencion);
        colaClient.cambiarEstado(atencion.getColaId(), new ColaClient.EstadoRequest("FINALIZADO"));
        return saved;
    }

    public List<AtencionMedica> findAll() {
        return atencionMedicaRepository.findAll();
    }
}

