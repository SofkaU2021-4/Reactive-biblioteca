package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarRecursoUseCase  implements GuardarDato{
    private final RecursoRepository repositorio;
    private final RecursoMapper mapper;

    public ActualizarRecursoUseCase(RecursoMapper mapper, RecursoRepository repositorio) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<Recurso> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "El id del recurso es requerido");
        return repositorio.save(mapper.mapToRecurso().apply(recursoDTO));
    }
}