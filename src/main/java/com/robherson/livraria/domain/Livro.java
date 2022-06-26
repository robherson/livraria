package com.robherson.livraria.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collation = "livros")
public class Livro {

    @Id
    private String id;

    private String titulo;
    private String autor;
    private String ano;
    private Boolean estaAlugado;
    
}
