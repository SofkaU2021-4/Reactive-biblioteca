package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Models.Recurso;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GuardarDato {
    public Mono<Recurso> apply(RecursoDTO recursoDTO);
}