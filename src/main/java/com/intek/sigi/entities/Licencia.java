
package com.intek.sigi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "licencia")
public class Licencia {

    @Id
    @Column(name = "id_caso")
    private Long idCaso;

    @Column(name = "tipo_licencia", nullable = false, length = 30)
    private String tipoLicencia;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "semanas_gestacion")
    private Integer semanasGestacion;

    public Licencia() {
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getSemanasGestacion() {
        return semanasGestacion;
    }

    public void setSemanasGestacion(Integer semanasGestacion) {
        this.semanasGestacion = semanasGestacion;
    }
}
