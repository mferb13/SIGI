package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "gestion_cobro")
public class GestionCobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gestion_cobro")
    private Long idGestionCobro;

    @Column(name = "id_caso", nullable = false)
    private Long idCaso;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "medio", length = 50)
    private String medio;

    @Column(name = "fundamento", columnDefinition = "TEXT")
    private String fundamento;

    @Column(name = "respuesta", columnDefinition = "TEXT")
    private String respuesta;

    @Column(name = "fecha_compromiso")
    private LocalDate fechaCompromiso;

    public GestionCobro() {
    }

    public Long getIdGestionCobro() {
        return idGestionCobro;
    }

    public void setIdGestionCobro(Long idGestionCobro) {
        this.idGestionCobro = idGestionCobro;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getFundamento() {
        return fundamento;
    }

    public void setFundamento(String fundamento) {
        this.fundamento = fundamento;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public LocalDate getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(LocalDate fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }
}