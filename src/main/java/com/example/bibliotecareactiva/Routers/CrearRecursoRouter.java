package com.example.bibliotecareactiva.Routers;


import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.UseCases.CrearRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> createQuestion(CrearRecursoUseCase crearRecursoUseCase) {
        return route(POST("/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(questionDTO -> crearRecursoUseCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        ).onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}