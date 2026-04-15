package com.sighd.msadmin.application.service;

import com.sighd.msadmin.domain.model.ReglaPriorizacion;
import com.sighd.msadmin.infrastructure.client.AtencionClient;
import com.sighd.msadmin.infrastructure.client.AuthAdminClient;
import com.sighd.msadmin.infrastructure.repository.ReglaPriorizacionRepository;
import com.sighd.msadmin.interfaces.rest.dto.AdminDtos;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final AuthAdminClient authAdminClient;
    private final AtencionClient atencionClient;
    private final ReglaPriorizacionRepository reglaRepository;

    public AdminService(AuthAdminClient authAdminClient,
                        AtencionClient atencionClient,
                        ReglaPriorizacionRepository reglaRepository) {
        this.authAdminClient = authAdminClient;
        this.atencionClient = atencionClient;
        this.reglaRepository = reglaRepository;
    }

    public List<Map<String, Object>> listarUsuarios() {
        return authAdminClient.usuarios();
    }

    public Map<String, Object> crearUsuario(AdminDtos.UsuarioCreateRequest request) {
        return authAdminClient.create(Map.of(
                "username", request.username(),
                "password", request.password(),
                "rol", request.rol(),
                "pacienteId", request.pacienteId(),
                "activo", request.activo()
        ));
    }

    public Map<String, Object> actualizarUsuario(Long id, AdminDtos.UsuarioUpdateRequest request) {
        return authAdminClient.update(id, Map.of(
                "username", request.username(),
                "rol", request.rol(),
                "pacienteId", request.pacienteId(),
                "activo", request.activo()
        ));
    }

    public void eliminarUsuario(Long id) {
        authAdminClient.delete(id);
    }

    public List<ReglaPriorizacion> listarReglas() {
        return reglaRepository.findAll();
    }

    public List<ReglaPriorizacion> actualizarReglas(AdminDtos.ReglaBatchRequest request) {
        reglaRepository.deleteAll();
        List<ReglaPriorizacion> reglas = request.reglas().stream().map(r -> {
            ReglaPriorizacion e = new ReglaPriorizacion();
            e.setNombre(r.nombre());
            e.setCondicion(r.condicion());
            e.setNivelAsignado(r.nivelAsignado());
            return e;
        }).toList();
        return reglaRepository.saveAll(reglas);
    }

    public byte[] generarReporteCsvAtenciones() {
        List<Map<String, Object>> atenciones = atencionClient.atenciones();
        StringBuilder csv = new StringBuilder("id,pacienteId,medicoId,diagnostico,tratamiento,requiereHospitalizacion,fechaAtencion,finalizada\n");
        for (Map<String, Object> a : atenciones) {
            csv.append(a.getOrDefault("id", "")).append(",")
                    .append(a.getOrDefault("pacienteId", "")).append(",")
                    .append(a.getOrDefault("medicoId", "")).append(",")
                    .append(escape(a.get("diagnostico"))).append(",")
                    .append(escape(a.get("tratamiento"))).append(",")
                    .append(a.getOrDefault("requiereHospitalizacion", "")).append(",")
                    .append(a.getOrDefault("fechaAtencion", "")).append(",")
                    .append(a.getOrDefault("finalizada", "")).append("\n");
        }
        return csv.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String escape(Object value) {
        if (value == null) {
            return "";
        }
        return "\"" + value.toString().replace("\"", "\"\"") + "\"";
    }
}

