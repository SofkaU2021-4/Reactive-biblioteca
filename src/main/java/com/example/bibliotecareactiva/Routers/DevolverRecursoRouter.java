package com.example.bibliotecareactiva.Routers;


import com.example.bibliotecareactiva.UseCases.DevolverRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DevolverRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> Devolver(DevolverRecursoUseCase devolverRecursoUseCase) {
        return route(
                PUT("/recursos/devolver/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters
                                .fromPublisher(devolverRecursoUseCase
                                        .apply(request.pathVariable("id")), String.class))

        );
    }



}
