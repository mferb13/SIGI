package com.intek.sigi.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long idAlerta;

    @Column(name = "id_caso")
    private Long idCaso;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "generada_el", nullable = false)
    private LocalDateTime generadaEl;

    @Column(name = "prioridad", nullable = false)
    private Integer prioridad;

    @Column(name = "atendida", nullable = false)
    private boolean atendida = false;

    @Column(name = "atendida_el")
    private LocalDateTime atendidaEl;

    @Column(name = "escalada", nullable = false)
    private boolean escalada = false;

    public Alerta() {
    }

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getGeneradaEl() {
        return generadaEl;
    }

    public void setGeneradaEl(LocalDateTime generadaEl) {
        this.generadaEl = generadaEl;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    public LocalDateTime getAtendidaEl() {
        return atendidaEl;
    }

    public void setAtendidaEl(LocalDateTime atendidaEl) {
        this.atendidaEl = atendidaEl;
    }

    public boolean isEscalada() {
        return escalada;
    }

    public void setEscalada(boolean escalada) {
        this.escalada = escalada;
    }
}