package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asignacion_reemplazo")
public class AsignacionReemplazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;

    @Column(name = "id_colaborador_reemplazado", nullable = false)
    private Integer idColaboradorReemplazado;

    @Column(name = "id_colaborador_reemplazo", nullable = false)
    private Integer idColaboradorReemplazo;

    @Column(name = "desde", nullable = false)
    private LocalDate desde;

    @Column(name = "hasta")
    private LocalDate hasta;

    @Column(name = "registrada_por", length = 150)
    private String registradaPor;

    public AsignacionReemplazo() {
    }

    public Long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Integer getIdColaboradorReemplazado() {
        return idColaboradorReemplazado;
    }

    public void setIdColaboradorReemplazado(Integer idColaboradorReemplazado) {
        this.idColaboradorReemplazado = idColaboradorReemplazado;
    }

    public Integer getIdColaboradorReemplazo() {
        return idColaboradorReemplazo;
    }

    public void setIdColaboradorReemplazo(Integer idColaboradorReemplazo) {
        this.idColaboradorReemplazo = idColaboradorReemplazo;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

    public String getRegistradaPor() {
        return registradaPor;
    }

    public void setRegistradaPor(String registradaPor) {
        this.registradaPor = registradaPor;
    }
}