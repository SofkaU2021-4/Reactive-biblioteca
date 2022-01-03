package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListarRecursosUseCase implements Supplier<Flux<RecursoDTO>> {
    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public ListarRecursosUseCase(RecursoMapper recursoMapper, RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return recursoRepository.findAll().map(recursoMapper.mapToRecursoDTO());
    }
}













