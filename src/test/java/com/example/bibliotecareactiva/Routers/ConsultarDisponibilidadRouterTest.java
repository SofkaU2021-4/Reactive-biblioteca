package com.example.bibliotecareactiva.Routers;


import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import com.example.bibliotecareactiva.UseCases.ConsultarDisponibilidadUseCase;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConsultarDisponibilidadRouter.class, ConsultarDisponibilidadUseCase.class, RecursoMapper.class})
class ConsultarDisponibilidadRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;


    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testDisponibilidadRecurso(){
        Recurso recurso = new Recurso();
        recurso.setNombre("el libro de la selva");
        recurso.setAreaTematica(AreaTematica.INFANTIL);
        recurso.setTipoRecurso(TipoRecuerso.LIBRO);
        recurso.setId("xxx");
        recurso.setFechaPrestamo(new Date(12245678));
        recurso.setDisponible(false);

        when(recursoRepository.findById(recurso.getId())).thenReturn(Mono.just(recurso));


        webTestClient.get()
                .uri("/recursos/disponible/{id}", "xxx")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse)
                                    .isEqualTo("El recurso " + recurso.getNombre() + " no esta disponible, y se presto el: " + recurso.getFechaPrestamo());
                        }
                );

    }



}