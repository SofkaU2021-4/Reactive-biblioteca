package com.example.bibliotecareactiva.Repository;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Models.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface RecursoRepository extends ReactiveMongoRepository<Recurso,String> {
    Mono<RecursoDTO> findByIdAndDisponibleTrue(String id);
    Flux<RecursoDTO> findByTipoRecursoAndAreaTematica(TipoRecuerso tipoRecuerso , AreaTematica areaTematica);
    Flux<RecursoDTO> findByTipoRecurso(TipoRecuerso tipoRecuerso);
    Flux<RecursoDTO> findByAreaTematica(AreaTematica areaTematica);



}
