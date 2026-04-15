package com.sighd.mscola.infrastructure.repository;

import com.sighd.mscola.domain.enums.EstadoCola;
import com.sighd.mscola.domain.model.ColaAtencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaAtencionRepository extends JpaRepository<ColaAtencion, Long> {
    List<ColaAtencion> findByEstadoOrderByNivelPrioridadAscFechaIngresoAsc(EstadoCola estado);
}

