package com.example.bibliotecareactiva.Models;



import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.Objects;

@Document("Recursos")
public class Recurso {

    @Id
    private String id;

    private String nombre;
    private boolean isDisponible = true;
    private Date fechaPrestamo;
    private AreaTematica areaTematica ;
    private TipoRecuerso tipoRecurso ;

    public Recurso() {
    }

    public Recurso(String nombre, AreaTematica areaTematica, TipoRecuerso tipoRecurso) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
    }

    public Recurso(String id, String nombre, AreaTematica areaTematica, TipoRecuerso tipoRecurso, Boolean isDisponible) {
        this.id=id;
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipoRecurso = tipoRecurso;
        this.isDisponible=isDisponible;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public void setTipoRecurso(TipoRecuerso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return isDisponible;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recurso = (Recurso) o;
        return isDisponible == recurso.isDisponible && Objects.equals(id, recurso.id) && Objects.equals(nombre, recurso.nombre) && Objects.equals(fechaPrestamo, recurso.fechaPrestamo) && areaTematica == recurso.areaTematica && tipoRecurso == recurso.tipoRecurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, isDisponible, fechaPrestamo, areaTematica, tipoRecurso);
    }
}
