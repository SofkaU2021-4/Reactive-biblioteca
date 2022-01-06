package com.example.bibliotecareactiva.Routers;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import com.example.bibliotecareactiva.UseCases.EliminarRecursoUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarRecursoRouter.class, EliminarRecursoUseCase.class, RecursoMapper.class})
class EliminarRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testEliminarRecurso(){


        Recurso recurso = new Recurso();
        recurso.setNombre("el libro de la selva");
        recurso.setAreaTematica(AreaTematica.INFANTIL);
        recurso.setTipoRecurso(TipoRecuerso.LIBRO);
        recurso.setId("1");

        Mono<Void>  vacio= Mono.empty();

        when(recursoRepository.findById("1")).thenReturn(Mono.just(recurso));
        when(recursoRepository.deleteById("1")).thenReturn(vacio);

        webTestClient.delete()
                .uri("/recursos/eliminar/{id}", "1")
                .exchange()
                .expectStatus()
                .isAccepted()
                .expectBody(String.class)
                .value(userResponse -> {
                        Assertions.assertThat(userResponse).isEqualTo("borrado");
                    });


    }


}