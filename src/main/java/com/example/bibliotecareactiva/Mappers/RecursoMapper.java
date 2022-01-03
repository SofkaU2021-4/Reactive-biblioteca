package com.example.bibliotecareactiva.Mappers;



import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Models.Recurso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


@Component
public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto){
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setAreaTematica(dto.getAreaTematica());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setNombre(dto.getNombre());
        recurso.setTipoRecurso(dto.getTipoRecurso());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection){
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setAreaTematica(collection.getAreaTematica());
        recursoDTO.setDisponible(collection.isDisponible());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setTipoRecurso(collection.getTipoRecurso());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection){
        if(collection==null){
            return null;
        }
        List<RecursoDTO> list = new ArrayList<>(collection.size());
        Iterator listTrack = collection.iterator();

        while(listTrack.hasNext()){
            Recurso recurso = (Recurso) listTrack.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }

    public Function<RecursoDTO, Recurso> mapToRecurso() {
        return updateRecurso -> {
            Recurso recurso = new Recurso();
            /*dato.setId(id);*/
            recurso.setId(updateRecurso.getId());
            recurso.setAreaTematica(updateRecurso.getAreaTematica());
            recurso.setDisponible(updateRecurso.isDisponible());
            recurso.setFechaPrestamo(updateRecurso.getFechaPrestamo());
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setTipoRecurso(updateRecurso.getTipoRecurso());
            return recurso;
        };
    }
    public Function<Recurso, RecursoDTO> mapToRecursoDTO() {
        return recursoDTONew ->{
            RecursoDTO recursoDTO=new RecursoDTO();
            recursoDTO.setId(recursoDTONew.getId());
            recursoDTO.setAreaTematica(recursoDTONew.getAreaTematica());
            recursoDTO.setDisponible(recursoDTONew.isDisponible());
            recursoDTO.setFechaPrestamo(recursoDTONew.getFechaPrestamo());
            recursoDTO.setNombre(recursoDTONew.getNombre());
            recursoDTO.setTipoRecurso(recursoDTONew.getTipoRecurso());
            return recursoDTO;

        };
    }



}
