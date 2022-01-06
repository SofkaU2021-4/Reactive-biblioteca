package com.example.bibliotecareactiva.UseCases;

import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class ConsultarDisponibilidadUseCase implements Function<String, Mono<String>> {
    private final RecursoRepository repositorio;

    public ConsultarDisponibilidadUseCase(RecursoRepository repositorio) {
        this.repositorio = repositorio;
    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return repositorio.findById(id)
                .map(recurso ->
                        recurso.isDisponible()
                                ? "El recurso " + recurso.getNombre() + " esta disponible"
                                : "El recurso " + recurso.getNombre() + " no esta disponible, y se presto el: " + recurso.getFechaPrestamo()
                );
    }
}
