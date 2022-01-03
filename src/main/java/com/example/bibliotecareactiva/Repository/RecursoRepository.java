package com.example.bibliotecareactiva.Repository;

import com.example.bibliotecareactiva.Models.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;



public interface RecursoRepository extends ReactiveMongoRepository<Recurso,String> {
}
