package com.sighd.mstriaje.infrastructure.client;

import com.sighd.mstriaje.domain.enums.NivelPrioridad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-cola", url = "${clients.ms-cola.url}", configuration = FeignTokenRelayConfig.class)
public interface ColaClient {

    @PostMapping("/api/cola/agregar")
    void agregar(@RequestBody AgregarColaRequest request);

    record AgregarColaRequest(Long pacienteId, NivelPrioridad nivelPrioridad) {
    }
}

