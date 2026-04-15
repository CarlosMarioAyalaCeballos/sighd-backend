package com.sighd.msadmin.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-atencion", url = "${clients.ms-atencion.url}", configuration = FeignTokenRelayConfig.class)
public interface AtencionClient {

    @GetMapping("/api/atencion")
    List<Map<String, Object>> atenciones();
}

