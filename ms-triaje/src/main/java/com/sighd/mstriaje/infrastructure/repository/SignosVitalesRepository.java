package com.sighd.mstriaje.infrastructure.repository;

import com.sighd.mstriaje.domain.model.SignosVitales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long> {
}

