package com.sighd.mscola.domain.model;

import com.sighd.mscola.domain.enums.EstadoCola;
import com.sighd.mscola.domain.enums.NivelPrioridad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "cola_atencion")
public class ColaAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pacienteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4)
    private NivelPrioridad nivelPrioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCola estado;

    @Column(nullable = false)
    private LocalDateTime fechaIngreso = LocalDateTime.now();

    private LocalDateTime fechaInicioAtencion;
    private LocalDateTime fechaFin;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public NivelPrioridad getNivelPrioridad() { return nivelPrioridad; }
    public void setNivelPrioridad(NivelPrioridad nivelPrioridad) { this.nivelPrioridad = nivelPrioridad; }
    public EstadoCola getEstado() { return estado; }
    public void setEstado(EstadoCola estado) { this.estado = estado; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public LocalDateTime getFechaInicioAtencion() { return fechaInicioAtencion; }
    public void setFechaInicioAtencion(LocalDateTime fechaInicioAtencion) { this.fechaInicioAtencion = fechaInicioAtencion; }
    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
}

