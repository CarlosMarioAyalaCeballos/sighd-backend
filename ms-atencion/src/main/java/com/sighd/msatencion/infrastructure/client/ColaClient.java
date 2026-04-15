package com.sighd.msatencion.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-cola", url = "${clients.ms-cola.url}", configuration = FeignTokenRelayConfig.class)
public interface ColaClient {

    @GetMapping("/api/cola")
    List<Map<String, Object>> colaPriorizada();

    @PatchMapping("/api/cola/{colaId}/estado")
    void cambiarEstado(@PathVariable("colaId") Long colaId, @RequestBody EstadoRequest request);

    record EstadoRequest(String estado) {
    }
}

