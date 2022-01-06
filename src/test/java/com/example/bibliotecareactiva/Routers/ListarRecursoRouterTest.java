package com.example.bibliotecareactiva.Routers;

import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Mappers.RecursoMapper;
import com.example.bibliotecareactiva.Models.Recurso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import com.example.bibliotecareactiva.UseCases.ListarRecursosUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarRecursoRouter.class, ListarRecursosUseCase.class, RecursoMapper.class})
class ListarRecursoRouterTest {
    @MockBean
    private RecursoRepository recursoRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testListarRecuerso(){
        Recurso recurso1 = new Recurso();
        recurso1.setNombre("el libro de la selva");
        recurso1.setAreaTematica(AreaTematica.INFANTIL);
        recurso1.setTipoRecurso(TipoRecuerso.LIBRO);
        Recurso recurso2 = new Recurso();
        recurso1.setNombre("algebra");
        recurso1.setAreaTematica(AreaTematica.MATEMATICAS);
        recurso1.setTipoRecurso(TipoRecuerso.LIBRO);

        when(recursoRepository.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.get(0).getTipoRecurso()).isEqualTo(recurso1.getTipoRecurso());
                            Assertions.assertThat(userResponse.get(0).getAreaTematica()).isEqualTo(recurso1.getAreaTematica());
                            Assertions.assertThat(userResponse.get(0).getTipoRecurso()).isEqualTo(recurso1.getTipoRecurso());
                            Assertions.assertThat(userResponse.get(1).getNombre()).isEqualTo(recurso2.getNombre());
                            Assertions.assertThat(userResponse.get(1).getTipoRecurso()).isEqualTo(recurso2.getTipoRecurso());
                            Assertions.assertThat(userResponse.get(1).getAreaTematica()).isEqualTo(recurso2.getAreaTematica());
                            Assertions.assertThat(userResponse.get(1).getTipoRecurso()).isEqualTo(recurso2.getTipoRecurso());;

                        }
                );


    }


}