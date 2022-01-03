package com.example.bibliotecareactiva.Routers;


import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.UseCases.ListarRecursosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> getAll(ListarRecursosUseCase listarRecursosUseCase) {
        return route(
                GET("/consultar").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listarRecursosUseCase.get(), RecursoDTO.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }


}