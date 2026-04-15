package com.sighd.msatencion.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "atenciones_medicas")
public class AtencionMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pacienteId;

    @Column(nullable = false)
    private Long medicoId;

    @Column(nullable = false, length = 1000)
    private String diagnostico;

    @Column(nullable = false, length = 1000)
    private String tratamiento;

    @Column(nullable = false)
    private Boolean requiereHospitalizacion;

    @Column(nullable = false)
    private LocalDateTime fechaAtencion = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean finalizada = false;

    @Column(nullable = false)
    private Long colaId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public Boolean getRequiereHospitalizacion() { return requiereHospitalizacion; }
    public void setRequiereHospitalizacion(Boolean requiereHospitalizacion) { this.requiereHospitalizacion = requiereHospitalizacion; }
    public LocalDateTime getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDateTime fechaAtencion) { this.fechaAtencion = fechaAtencion; }
    public Boolean getFinalizada() { return finalizada; }
    public void setFinalizada(Boolean finalizada) { this.finalizada = finalizada; }
    public Long getColaId() { return colaId; }
    public void setColaId(Long colaId) { this.colaId = colaId; }
}

