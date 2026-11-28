
package com.intek.sigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "incapacidad")
public class Incapacidad {

    @Id
    @Column(name = "id_caso")
    private Long idCaso;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_caso")
    private Caso caso;

    @Column(name = "origen", nullable = false, length = 30)
    private String origen;

    @Column(name = "es_prorroga", nullable = false)
    private boolean esProrroga;

    public Incapacidad() {
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public boolean isEsProrroga() {
        return esProrroga;
    }

    public void setEsProrroga(boolean esProrroga) {
        this.esProrroga = esProrroga;
    }
    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }
}
