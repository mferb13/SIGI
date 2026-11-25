
package com.intek.sigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_novedad")
public class TipoNovedad {

    @Id
    @Column(name = "codigo", length = 30)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "requiere_epicrisis", nullable = false)
    private boolean requiereEpicrisis;

    @Column(name = "dias_minimos_epicrisis")
    private Integer diasMinimosEpicrisis;

    public TipoNovedad() {
    }

    public TipoNovedad(String codigo, String nombre,
                       boolean requiereEpicrisis,
                       Integer diasMinimosEpicrisis) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.requiereEpicrisis = requiereEpicrisis;
        this.diasMinimosEpicrisis = diasMinimosEpicrisis;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isRequiereEpicrisis() {
        return requiereEpicrisis;
    }

    public void setRequiereEpicrisis(boolean requiereEpicrisis) {
        this.requiereEpicrisis = requiereEpicrisis;
    }

    public Integer getDiasMinimosEpicrisis() {
        return diasMinimosEpicrisis;
    }

    public void setDiasMinimosEpicrisis(Integer diasMinimosEpicrisis) {
        this.diasMinimosEpicrisis = diasMinimosEpicrisis;
    }
}
