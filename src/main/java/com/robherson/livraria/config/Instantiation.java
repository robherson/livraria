package com.robherson.livraria.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.robherson.livraria.domain.Livro;
import com.robherson.livraria.repository.LivroRepository;


@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    LivroRepository livroRepository;
    

    @Override
    public void run(String... args) throws Exception {

        if (livroRepository.count() == 0) {
            
            Livro livro1 = Livro.builder()
            .titulo("Mais Esperto que o Diabo")
            .autor("Napoleon Hill")
            .ano("2011")
            .estaAlugado(false)
            .build();
    
            Livro livro2 = Livro.builder()
            .titulo("O poder do hábito")
            .autor("Charles Duhigg")
            .ano("2012")
            .estaAlugado(false)
            .build();
    
            Livro livro3 = Livro.builder()
            .titulo("Pai Rico, Pai Pobre")
            .autor("Robert Kiyosaki, Sharon L. Lechterl")
            .ano("1997")
            .estaAlugado(false)
            .build();
    
    
            livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3));
        }
        
    }
    
}
