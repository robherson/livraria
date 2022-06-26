package com.robherson.livraria.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robherson.livraria.domain.Livro;
import com.robherson.livraria.dto.LivroDTO;
import com.robherson.livraria.exception.LivroAlugadoException;
import com.robherson.livraria.exception.LivroNotFoundException;
import com.robherson.livraria.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    public List<LivroDTO> findAll(){
        List<Livro> livros =  livroRepository.findAll();

        return livros.stream().map(l -> LivroDTO.fromLivro(l)).collect(Collectors.toList());
    }

    public LivroDTO findById(String id) throws Exception{
        return livroRepository.findById(id)
            .map(l -> LivroDTO.fromLivro(l))
            .orElseThrow(() -> new LivroNotFoundException());
    }

    public LivroDTO alugarLivro(String id) throws Exception{
        Livro livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNotFoundException());

        if(livro.getEstaAlugado()){
            throw new LivroAlugadoException();
        }
        
        livro.setEstaAlugado(true);
        livroRepository.save(livro);

        return LivroDTO.fromLivro(livro);
    }
    
}
