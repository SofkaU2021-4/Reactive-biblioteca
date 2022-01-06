package com.example.bibliotecareactiva.UseCases;


import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.core.codec.CodecException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase implements Function<String, Mono<String>> {
    private final RecursoRepository repositorio;



    public EliminarRecursoUseCase( RecursoRepository repositorio) {
        this.repositorio = repositorio;

    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "Id es requerido");
        return repositorio.findById(id)
                .flatMap(recurso -> {
                    return repositorio.deleteById(recurso.getId()).thenReturn("borrado");
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND , "id erroneo")));

    }
}
