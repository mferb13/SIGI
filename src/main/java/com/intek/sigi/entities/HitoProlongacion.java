package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hito_prolongacion")
public class HitoProlongacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hito")
    private Long idHito;

    @Column(name = "id_caso", nullable = false)
    private Long idCaso;

    @Column(name = "dias", nullable = false)
    private Integer dias;

    @Column(name = "fecha_alcanzado", nullable = false)
    private LocalDate fechaAlcanzado;

    @Column(name = "fecha_solicitud_concepto")
    private LocalDate fechaSolicitudConcepto;

    @Column(name = "fecha_concepto")
    private LocalDate fechaConcepto;

    @Column(name = "concepto", length = 30)
    private String concepto;

    @Column(name = "enviado_afp", nullable = false)
    private boolean enviadoAfp = false;

    @Column(name = "comunicado_colaborador", nullable = false)
    private boolean comunicadoColaborador = false;

    public HitoProlongacion() {
    }

    public Long getIdHito() {
        return idHito;
    }

    public void setIdHito(Long idHito) {
        this.idHito = idHito;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public LocalDate getFechaAlcanzado() {
        return fechaAlcanzado;
    }

    public void setFechaAlcanzado(LocalDate fechaAlcanzado) {
        this.fechaAlcanzado = fechaAlcanzado;
    }

    public LocalDate getFechaSolicitudConcepto() {
        return fechaSolicitudConcepto;
    }

    public void setFechaSolicitudConcepto(LocalDate fechaSolicitudConcepto) {
        this.fechaSolicitudConcepto = fechaSolicitudConcepto;
    }

    public LocalDate getFechaConcepto() {
        return fechaConcepto;
    }

    public void setFechaConcepto(LocalDate fechaConcepto) {
        this.fechaConcepto = fechaConcepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public boolean isEnviadoAfp() {
        return enviadoAfp;
    }

    public void setEnviadoAfp(boolean enviadoAfp) {
        this.enviadoAfp = enviadoAfp;
    }

    public boolean isComunicadoColaborador() {
        return comunicadoColaborador;
    }

    public void setComunicadoColaborador(boolean comunicadoColaborador) {
        this.comunicadoColaborador = comunicadoColaborador;
    }
}