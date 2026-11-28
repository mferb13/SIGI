
package com.intek.sigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "caso")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caso")
    private Long idCaso;

    @Column(name = "consecutivo", length = 30)
    private String consecutivo;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDate fechaRecepcion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "canal_recepcion", nullable = false, length = 30)
    private String canalRecepcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "dias", nullable = false)
    private Integer dias;

    @Column(name = "estado", nullable = false, length = 40)
    private String estado;

    @Column(name = "fecha_limite_transcripcion")
    private LocalDate fechaLimiteTranscripcion;

    @Column(name = "registro_tardio", nullable = false)
    private boolean registroTardio;

    @Column(name = "valor_liquidado", precision = 14, scale = 2)
    private BigDecimal valorLiquidado;

    @Column(name = "saldo", precision = 14, scale = 2)
    private BigDecimal saldo;

    @Column(name = "codigo_tipo_novedad", nullable = false, length = 30)
    private String codigoTipoNovedad;

    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    public Caso() {
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCanalRecepcion() {
        return canalRecepcion;
    }

    public void setCanalRecepcion(String canalRecepcion) {
        this.canalRecepcion = canalRecepcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaLimiteTranscripcion() {
        return fechaLimiteTranscripcion;
    }

    public void setFechaLimiteTranscripcion(LocalDate fechaLimiteTranscripcion) {
        this.fechaLimiteTranscripcion = fechaLimiteTranscripcion;
    }

    public boolean isRegistroTardio() {
        return registroTardio;
    }

    public void setRegistroTardio(boolean registroTardio) {
        this.registroTardio = registroTardio;
    }

    public BigDecimal getValorLiquidado() {
        return valorLiquidado;
    }

    public void setValorLiquidado(BigDecimal valorLiquidado) {
        this.valorLiquidado = valorLiquidado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getCodigoTipoNovedad() {
        return codigoTipoNovedad;
    }

    public void setCodigoTipoNovedad(String codigoTipoNovedad) {
        this.codigoTipoNovedad = codigoTipoNovedad;
    }
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
