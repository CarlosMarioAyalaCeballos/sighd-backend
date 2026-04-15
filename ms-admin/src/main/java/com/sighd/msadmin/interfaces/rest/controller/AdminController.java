package com.sighd.msadmin.interfaces.rest.controller;

import com.sighd.msadmin.application.service.AdminService;
import com.sighd.msadmin.interfaces.rest.dto.AdminDtos;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/usuarios")
    public Object usuarios() {
        return adminService.listarUsuarios();
    }

    @PostMapping("/usuarios")
    public Object crearUsuario(@Valid @RequestBody AdminDtos.UsuarioCreateRequest request) {
        return adminService.crearUsuario(request);
    }

    @PutMapping("/usuarios/{id}")
    public Object actualizarUsuario(@PathVariable Long id, @Valid @RequestBody AdminDtos.UsuarioUpdateRequest request) {
        return adminService.actualizarUsuario(id, request);
    }

    @DeleteMapping("/usuarios/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        adminService.eliminarUsuario(id);
    }

    @GetMapping("/reglas")
    public Object reglas() {
        return adminService.listarReglas();
    }

    @PutMapping("/reglas")
    public Object actualizarReglas(@Valid @RequestBody AdminDtos.ReglaBatchRequest request) {
        return adminService.actualizarReglas(request);
    }

    @GetMapping("/reportes/atencion")
    public ResponseEntity<byte[]> reportesAtencion() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_atenciones.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(adminService.generarReporteCsvAtenciones());
    }
}

