package com.intek.sigi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "requisito_documental")
public class RequisitoDocumental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito")
    private Long idRequisito;

    @Column(name = "codigo_tipo_novedad", nullable = false, length = 30)
    private String codigoTipoNovedad;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    private String tipoDocumento;

    @Column(name = "obligatorio", nullable = false)
    private boolean obligatorio;

    @Column(name = "condicion", columnDefinition = "TEXT")
    private String condicion;

    public RequisitoDocumental() {
    }

    public Long getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Long idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getCodigoTipoNovedad() {
        return codigoTipoNovedad;
    }

    public void setCodigoTipoNovedad(String codigoTipoNovedad) {
        this.codigoTipoNovedad = codigoTipoNovedad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}