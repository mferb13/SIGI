package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_conciliacion")
public class DetalleConciliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "id_conciliacion", nullable = false)
    private Long idConciliacion;

    @Column(name = "valor_contabilidad", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorContabilidad;

    @Column(name = "valor_registrado", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorRegistrado;

    @Column(name = "diferencia", nullable = false, precision = 14, scale = 2)
    private BigDecimal diferencia;

    @Column(name = "identificado", nullable = false)
    private boolean identificado = false;

    public DetalleConciliacion() {
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdConciliacion() {
        return idConciliacion;
    }

    public void setIdConciliacion(Long idConciliacion) {
        this.idConciliacion = idConciliacion;
    }

    public BigDecimal getValorContabilidad() {
        return valorContabilidad;
    }

    public void setValorContabilidad(BigDecimal valorContabilidad) {
        this.valorContabilidad = valorContabilidad;
    }

    public BigDecimal getValorRegistrado() {
        return valorRegistrado;
    }

    public void setValorRegistrado(BigDecimal valorRegistrado) {
        this.valorRegistrado = valorRegistrado;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public boolean isIdentificado() {
        return identificado;
    }

    public void setIdentificado(boolean identificado) {
        this.identificado = identificado;
    }
}