
package com.intek.sigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "causal_rechazo")
public class CausalRechazo {

    @Id
    @Column(name = "codigo", length = 30)
    private String codigo;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "subsanable", nullable = false)
    private boolean subsanable;

    public CausalRechazo() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSubsanable() {
        return subsanable;
    }

    public void setSubsanable(boolean subsanable) {
        this.subsanable = subsanable;
    }
}
