package com.sighd.mstriaje.infrastructure.repository;

import com.sighd.mstriaje.domain.model.Triaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TriajeRepository extends JpaRepository<Triaje, Long> {
    List<Triaje> findByPacienteIdOrderByFechaHoraDesc(Long pacienteId);
}

