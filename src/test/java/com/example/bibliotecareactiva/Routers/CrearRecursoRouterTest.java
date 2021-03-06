package com.example.bibliotecareactiva.Routers;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import com.example.bibliotecareactiva.UseCases.CrearRecursoUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, CrearRecursoUseCase.class, RecursoMapper.class})
class CrearRecursoRouterTest {

    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testCreateRecurso(){

        Recurso recurso = new Recurso();
        recurso.setNombre("el libro de la selva");
        recurso.setAreaTematica(AreaTematica.INFANTIL);
        recurso.setTipoRecurso(TipoRecuerso.LIBRO);

        RecursoDTO recursoDTO = new RecursoDTO(
                recurso.getNombre(),
                recurso.getAreaTematica(),
                recurso.getTipoRecurso()
        );

        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(recursoRepository.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo(recursoDTO);
                        }
                );


    }


}