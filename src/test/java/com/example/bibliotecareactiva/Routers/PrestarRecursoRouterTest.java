package com.example.bibliotecareactiva.Routers;

import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import com.example.bibliotecareactiva.UseCases.PrestarRecursoUseCase;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PrestarRecursoRouter.class, PrestarRecursoUseCase.class, RecursoMapper.class})
class PrestarRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testPrestarRecuerso(){

        Recurso recurso = new Recurso();
        recurso.setNombre("el libro de la selva");
        recurso.setAreaTematica(AreaTematica.INFANTIL);
        recurso.setTipoRecurso(TipoRecuerso.LIBRO);
        recurso.setId("xxx");
        recurso.setDisponible(true);

        Recurso recurso1 = new Recurso();
        recurso1.setId(recurso.getId());
        recurso1.setNombre(recurso.getNombre());
        recurso1.setTipoRecurso(recurso.getTipoRecurso());
        recurso1.setAreaTematica(recurso.getAreaTematica());
        recurso1.setDisponible(false);


        Mono<Recurso> recursoMono = Mono.just(recurso);
        Mono<Recurso> recursoMono1 = Mono.just(recurso1);
        when(recursoRepository.findById(recurso.getId())).thenReturn(recursoMono);
        when(recursoRepository.save(any())).thenReturn(recursoMono1);


        webTestClient.put()
                .uri("/recursos/prestar/{id}", "xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("recurso prestado ");
                        }
                );


    }

}