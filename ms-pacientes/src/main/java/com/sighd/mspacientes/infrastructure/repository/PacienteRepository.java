package com.sighd.mspacientes.infrastructure.repository;

import com.sighd.mspacientes.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

