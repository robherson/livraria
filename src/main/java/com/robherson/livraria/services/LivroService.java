package com.robherson.livraria.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robherson.livraria.domain.Livro;
import com.robherson.livraria.dto.LivroDTO;
import com.robherson.livraria.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    public List<LivroDTO> findAll(){
        List<Livro> livros =  livroRepository.findAll();

        return livros.stream().map(l -> LivroDTO.fromLivro(l)).collect(Collectors.toList());
    }
    
}
