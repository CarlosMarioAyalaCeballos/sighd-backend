package com.sighd.mstriaje.infrastructure.repository;

import com.sighd.mstriaje.domain.model.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SintomaRepository extends JpaRepository<Sintoma, Long> {
}

