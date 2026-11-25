package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aviso_faltantes")
public class AvisoFaltantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aviso")
    private Long idAviso;

    @Column(name = "id_caso", nullable = false)
    private Long idCaso;

    @Column(name = "numero_llamado", nullable = false)
    private Integer numeroLlamado;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;

    @Column(name = "documentos_faltantes", nullable = false, columnDefinition = "TEXT")
    private String documentosFaltantes;

    @Column(name = "citado", nullable = false)
    private boolean citado = false;

    public AvisoFaltantes() {
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public Integer getNumeroLlamado() {
        return numeroLlamado;
    }

    public void setNumeroLlamado(Integer numeroLlamado) {
        this.numeroLlamado = numeroLlamado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getDocumentosFaltantes() {
        return documentosFaltantes;
    }

    public void setDocumentosFaltantes(String documentosFaltantes) {
        this.documentosFaltantes = documentosFaltantes;
    }

    public boolean isCitado() {
        return citado;
    }

    public void setCitado(boolean citado) {
        this.citado = citado;
    }
}