package com.robherson.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robherson.livraria.dto.LivroDTO;
import com.robherson.livraria.services.LivroService;

@RestController
@RequestMapping(value = "/api/livros")
public class LivroController {

    @Autowired
    LivroService livroService;
    
    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(){
        List<LivroDTO> livrosDto = livroService.findAll();

        return ResponseEntity.ok().body(livrosDto);
    }    

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable String id) throws Exception{        
        LivroDTO livroDto = livroService.findById(id);
        return ResponseEntity.ok().body(livroDto);
    }
    
}
