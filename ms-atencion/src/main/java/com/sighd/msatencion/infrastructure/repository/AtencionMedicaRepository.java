package com.sighd.msatencion.infrastructure.repository;

import com.sighd.msatencion.domain.model.AtencionMedica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtencionMedicaRepository extends JpaRepository<AtencionMedica, Long> {
}

