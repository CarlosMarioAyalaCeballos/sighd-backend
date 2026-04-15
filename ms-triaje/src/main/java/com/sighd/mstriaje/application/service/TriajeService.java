package com.sighd.mstriaje.application.service;

import com.sighd.mstriaje.domain.enums.NivelPrioridad;
import com.sighd.mstriaje.domain.model.SignosVitales;
import com.sighd.mstriaje.domain.model.Sintoma;
import com.sighd.mstriaje.domain.model.Triaje;
import com.sighd.mstriaje.infrastructure.client.ColaClient;
import com.sighd.mstriaje.infrastructure.repository.SignosVitalesRepository;
import com.sighd.mstriaje.infrastructure.repository.SintomaRepository;
import com.sighd.mstriaje.infrastructure.repository.TriajeRepository;
import com.sighd.mstriaje.interfaces.rest.dto.TriajeDtos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriajeService {

    private final SignosVitalesRepository signosRepository;
    private final SintomaRepository sintomaRepository;
    private final TriajeRepository triajeRepository;
    private final ColaClient colaClient;

    public TriajeService(SignosVitalesRepository signosRepository,
                        SintomaRepository sintomaRepository,
                        TriajeRepository triajeRepository,
                        ColaClient colaClient) {
        this.signosRepository = signosRepository;
        this.sintomaRepository = sintomaRepository;
        this.triajeRepository = triajeRepository;
        this.colaClient = colaClient;
    }

    public SignosVitales guardarSignos(TriajeDtos.SignosRequest req) {
        SignosVitales s = new SignosVitales();
        s.setPacienteId(req.pacienteId());
        s.setFrecuenciaCardiaca(req.frecuenciaCardiaca());
        s.setPresionArterial(req.presionArterial());
        s.setTemperatura(req.temperatura());
        s.setSaturacion(req.saturacion());
        return signosRepository.save(s);
    }

    public Sintoma guardarSintoma(TriajeDtos.SintomaRequest req) {
        Sintoma s = new Sintoma();
        s.setPacienteId(req.pacienteId());
        s.setDescripcion(req.descripcion());
        return sintomaRepository.save(s);
    }

    public Triaje clasificar(TriajeDtos.ClasificarRequest req) {
        NivelPrioridad nivel = calcularPrioridad(req);
        Triaje t = new Triaje();
        t.setPacienteId(req.pacienteId());
        t.setNivelPrioridad(nivel);
        t.setActivo(true);

        Triaje saved = triajeRepository.save(t);
        colaClient.agregar(new ColaClient.AgregarColaRequest(req.pacienteId(), nivel));
        return saved;
    }

    public List<Triaje> historial(Long pacienteId) {
        return triajeRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);
    }

    private NivelPrioridad calcularPrioridad(TriajeDtos.ClasificarRequest req) {
        if (req.saturacion() < 90) {
            return NivelPrioridad.I;
        }
        if (req.temperatura() > 39.0) {
            return NivelPrioridad.II;
        }
        if (req.frecuenciaCardiaca() > 120) {
            return NivelPrioridad.III;
        }

        boolean sintomaModerado = req.sintomas() != null && req.sintomas().stream()
                .map(String::toLowerCase)
                .anyMatch(s -> s.contains("dolor") || s.contains("disnea") || s.contains("mareo"));

        return sintomaModerado ? NivelPrioridad.IV : NivelPrioridad.V;
    }
}

