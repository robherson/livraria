package com.robherson.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        
        return ResponseEntity.ok().body(livroService.findById(id));
    }

    @PutMapping(value = "alugar/{id}")
    public ResponseEntity<LivroDTO> alugarLivro(@PathVariable String id) throws Exception{

        return ResponseEntity.ok().body(livroService.alugarLivro(id));
    }

    @PutMapping(value = "devolver/{id}")
    public ResponseEntity<LivroDTO> devolverLivro(@PathVariable String id) throws Exception{

        return ResponseEntity.ok().body(livroService.devolverLivro(id));
    }

    @PutMapping()
    public ResponseEntity<LivroDTO> editarLivro(@RequestBody LivroDTO livroDTO) throws Exception{

        return ResponseEntity.ok().body(livroService.editarLivro(livroDTO));
    }
    
}
