package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transcripcion")
public class Transcripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transcripcion")
    private Long idTranscripcion;

    @Column(name = "id_caso", nullable = false)
    private Long idCaso;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "medio", nullable = false, length = 30)
    private String medio;

    @Column(name = "numero_radicado", length = 100)
    private String numeroRadicado;

    @Column(name = "evidencia", length = 500)
    private String evidencia;

    @Column(name = "dentro_de_plazo", nullable = false)
    private boolean dentroDePlazo;

    public Transcripcion() {
    }

    public Long getIdTranscripcion() {
        return idTranscripcion;
    }

    public void setIdTranscripcion(Long idTranscripcion) {
        this.idTranscripcion = idTranscripcion;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public boolean isDentroDePlazo() {
        return dentroDePlazo;
    }

    public void setDentroDePlazo(boolean dentroDePlazo) {
        this.dentroDePlazo = dentroDePlazo;
    }
}