package com.example.bibliotecareactiva.Routers;


import com.example.bibliotecareactiva.UseCases.EliminarRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EliminarRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> delete(EliminarRecursoUseCase eliminarRecursoUseCase) {
        return route(
                DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters
                                .fromPublisher(eliminarRecursoUseCase
                                    .apply(request.pathVariable("id")), String.class))
                        .onErrorResume(throwable -> ServerResponse.badRequest().body(throwable.getMessage(),String.class))
        );
    }
}
