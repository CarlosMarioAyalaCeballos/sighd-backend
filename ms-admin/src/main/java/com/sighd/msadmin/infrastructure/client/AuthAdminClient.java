package com.sighd.msadmin.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "auth-service", url = "${clients.auth.url}", configuration = FeignTokenRelayConfig.class)
public interface AuthAdminClient {

    @GetMapping("/auth/users")
    List<Map<String, Object>> usuarios();

    @PostMapping("/auth/users")
    Map<String, Object> create(@RequestBody Map<String, Object> body);

    @PutMapping("/auth/users/{id}")
    Map<String, Object> update(@PathVariable("id") Long id, @RequestBody Map<String, Object> body);

    @DeleteMapping("/auth/users/{id}")
    void delete(@PathVariable("id") Long id);
}

