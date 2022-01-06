package com.example.bibliotecareactiva.Routers;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;

import com.example.bibliotecareactiva.UseCases.BuscarAreaYTipoUseCase;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecomendarPorAreaYTipo {
    @Bean
    public RouterFunction<ServerResponse> getAreaYTipo(BuscarAreaYTipoUseCase buscarAreaYTipoUseCase) {
        return route(
                GET("/recursos/area").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(buscarAreaYTipoUseCase.apply(request.queryParams()), RecursoDTO.class)
                        ));
    }
}
