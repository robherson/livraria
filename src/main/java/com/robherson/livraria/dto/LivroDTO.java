package com.robherson.livraria.dto;

import com.robherson.livraria.domain.Livro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivroDTO {
    private String id;
    private String titulo;
    private String autor;
    private String ano;
    private Boolean estaAlugado;

    public LivroDTO fromLivro(Livro livro){
        
        return LivroDTO.builder()
            .titulo(livro.getTitulo())
            .autor(livro.getAutor())
            .ano(livro.getAno())
            .estaAlugado(livro.getEstaAlugado())
            .build();
    }
}
