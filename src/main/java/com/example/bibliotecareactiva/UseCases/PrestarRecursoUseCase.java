package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@Validated
public class PrestarRecursoUseCase implements Function<String , Mono<String>> {

    private final RecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;


    public PrestarRecursoUseCase(RecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(String id) {
        var recursoUpdate= recursoRepository.findById(id);

        return recursoUpdate.flatMap(
                value->{
                    if(value.isDisponible()){
                        value.setDisponible(false);
                        value.setFechaPrestamo(new Date());
                        return  recursoRepository.save(value).thenReturn("recurso prestado ");
                    }
                    return Mono.just("paila ñeco");
                });
    }
}
