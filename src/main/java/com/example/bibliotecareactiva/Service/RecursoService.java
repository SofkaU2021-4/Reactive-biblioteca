package com.example.bibliotecareactiva.Service;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class RecursoService {
    @Autowired
    RecursoRepository recursoRepository;
    RecursoMapper mapper = new RecursoMapper();

    public Mono<RecursoDTO> buscarPorId(String id){

        return  recursoRepository.findById(id).map(mapper.mapToRecursoDTO());

    }

    public Mono<RecursoDTO> save(RecursoDTO recursoDTO) {
        return recursoRepository.save(mapper.mapToRecurso().apply(recursoDTO)).map(mapper.mapToRecursoDTO());
    }



    public Mono<String> prestarRecurso(String id){

        return buscarPorId(id).flatMap(r->{
           if(r.isDisponible()){
               r.setDisponible(false);
               r.setFechaPrestamo(new Date());
                save(r);
                return Mono.just("oe todo bn");
           }
           return Mono.error(new IllegalArgumentException("oe"));
        });
    }


}
