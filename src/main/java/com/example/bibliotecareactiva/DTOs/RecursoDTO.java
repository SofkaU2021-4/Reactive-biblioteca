package com.example.bibliotecareactiva.DTOs;


import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

public class RecursoDTO {

    private String id;
    @NotBlank
    private String nombre;
    private Boolean disponible = true;
    private Date fechaPrestamo;
    @NotBlank
    private AreaTematica areaTematica ;
    @NotBlank
    private TipoRecuerso tipoRecurso ;

    public RecursoDTO() {
    }

    public RecursoDTO(String nombre, AreaTematica areaTematica, TipoRecuerso tipoRecurso) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }



    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public TipoRecuerso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public void setTipoRecurso(TipoRecuerso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return disponible == that.disponible && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaPrestamo, that.fechaPrestamo) && areaTematica == that.areaTematica && tipoRecurso == that.tipoRecurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, disponible, fechaPrestamo, areaTematica, tipoRecurso);
    }
}
