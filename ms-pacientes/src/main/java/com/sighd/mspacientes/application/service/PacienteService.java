package com.sighd.mspacientes.application.service;

import com.sighd.mspacientes.domain.model.Paciente;
import com.sighd.mspacientes.infrastructure.repository.PacienteRepository;
import com.sighd.mspacientes.interfaces.rest.dto.PacienteDtos;
import com.sighd.mspacientes.shared.exception.NotFoundException;
import com.sighd.mspacientes.shared.mapper.PacienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteDtos.PacienteResponse create(PacienteDtos.PacienteRequest request) {
        Paciente entity = pacienteMapper.toEntity(request);
        return pacienteMapper.toResponse(pacienteRepository.save(entity));
    }

    public List<PacienteDtos.PacienteResponse> findAll() {
        return pacienteRepository.findAll().stream().map(pacienteMapper::toResponse).toList();
    }

    public PacienteDtos.PacienteResponse findById(Long id) {
        Paciente entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente no existe"));
        return pacienteMapper.toResponse(entity);
    }

    public PacienteDtos.PacienteResponse update(Long id, PacienteDtos.PacienteRequest request) {
        Paciente entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente no existe"));
        pacienteMapper.update(entity, request);
        return pacienteMapper.toResponse(pacienteRepository.save(entity));
    }
}

