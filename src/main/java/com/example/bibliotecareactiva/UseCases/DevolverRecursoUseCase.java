package com.example.bibliotecareactiva.UseCases;


import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Validated
public class DevolverRecursoUseCase implements Function<String, Mono<String>> {
    private final RecursoRepository repositorio;



    public DevolverRecursoUseCase(RecursoRepository repositorio) {
        this.repositorio = repositorio;
    }


    @Override
    public Mono<String> apply(String id) {
        return repositorio.findById(id).flatMap(
                value->{
                    if(!value.isDisponible()){
                        value.setDisponible(true);
                        value.setFechaPrestamo(new Date());
                        return  repositorio.save(value).thenReturn("recurso devuelto ");
                    }
                    return Mono.just("paila ñeco");
                });
    }
}

