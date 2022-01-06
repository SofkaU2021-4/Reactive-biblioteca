package com.example.bibliotecareactiva.UseCases;


import com.example.bibliotecareactiva.DTOs.RecursoDTO;
import com.example.bibliotecareactiva.Enums.AreaTematica;
import com.example.bibliotecareactiva.Enums.TipoRecuerso;
import com.example.bibliotecareactiva.Repository.RecursoRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;



@Service
@Validated
public class BuscarAreaYTipoUseCase implements Function<MultiValueMap<String,String> , Flux<RecursoDTO>> {

    private final RecursoRepository repositorio;


    public BuscarAreaYTipoUseCase(RecursoRepository repositorio) {
        this.repositorio = repositorio;

    }




    @Override
    public  Flux<RecursoDTO> apply(MultiValueMap<String, String> stringStringMultiValueMap) {

        if(stringStringMultiValueMap.containsKey("tipoRecurso")&&stringStringMultiValueMap.containsKey("areaTematica")){
            return repositorio.findByTipoRecursoAndAreaTematica(TipoRecuerso.valueOf(stringStringMultiValueMap.getFirst("tipoRecurso"))
                    , AreaTematica.valueOf(stringStringMultiValueMap.getFirst("areaTematica")));
        }else if(stringStringMultiValueMap.containsKey("areaTematica")){
            return repositorio.findByAreaTematica(AreaTematica.valueOf(stringStringMultiValueMap.getFirst("areaTematica")));

        }
        else if(stringStringMultiValueMap.containsKey("tipoRecurso")){
            return repositorio.findByTipoRecurso(TipoRecuerso.valueOf(stringStringMultiValueMap.getFirst("tipoRecurso")));
        }
        return Flux.error(new ResponseStatusException(HttpStatus.BAD_REQUEST , "datos del params erroneos"));

    }


}
