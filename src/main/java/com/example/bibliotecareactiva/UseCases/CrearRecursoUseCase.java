package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearRecursoUseCase implements GuardarDato {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;
    @Autowired
    public CrearRecursoUseCase(RecursoMapper recursoMapper, RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<Recurso> apply(RecursoDTO recursoDTO) {
        return recursoRepository.save(recursoMapper.mapToRecurso().apply(recursoDTO)).map(recurso -> recurso);
    }
}