package com.robherson.livraria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.robherson.livraria.domain.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {
    
}
