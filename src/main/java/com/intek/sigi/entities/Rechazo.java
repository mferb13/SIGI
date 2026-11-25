package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rechazo")
public class Rechazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rechazo")
    private Long idRechazo;

    @Column(name = "id_caso", nullable = false)
    private Long idCaso;

    @Column(name = "codigo_causal", length = 30)
    private String codigoCausal;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "subsanable", nullable = false)
    private boolean subsanable;

    @Column(name = "accion", length = 255)
    private String accion;

    @Column(name = "resultado", length = 255)
    private String resultado;

    public Rechazo() {
    }

    public Long getIdRechazo() {
        return idRechazo;
    }

    public void setIdRechazo(Long idRechazo) {
        this.idRechazo = idRechazo;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getCodigoCausal() {
        return codigoCausal;
    }

    public void setCodigoCausal(String codigoCausal) {
        this.codigoCausal = codigoCausal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isSubsanable() {
        return subsanable;
    }

    public void setSubsanable(boolean subsanable) {
        this.subsanable = subsanable;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}