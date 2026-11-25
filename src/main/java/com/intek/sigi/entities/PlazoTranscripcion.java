package com.intek.sigi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "plazo_transcripcion")
public class PlazoTranscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plazo")
    private Long idPlazo;

    @Column(name = "id_entidad", nullable = false)
    private Integer idEntidad;

    @Column(name = "codigo_tipo_novedad", nullable = false, length = 30)
    private String codigoTipoNovedad;

    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Column(name = "unidad", nullable = false, length = 30)
    private String unidad;

    @Column(name = "observacion", length = 255)
    private String observacion;

    public PlazoTranscripcion() {
    }

    public Long getIdPlazo() {
        return idPlazo;
    }

    public void setIdPlazo(Long idPlazo) {
        this.idPlazo = idPlazo;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getCodigoTipoNovedad() {
        return codigoTipoNovedad;
    }

    public void setCodigoTipoNovedad(String codigoTipoNovedad) {
        this.codigoTipoNovedad = codigoTipoNovedad;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}