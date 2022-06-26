package com.robherson.livraria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.robherson.livraria.domain.Livro;

public interface LivroRepository extends MongoRepository<Livro, String> {
    
}
