package com.robherson.livraria.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.robherson.livraria.domain.Livro;
import com.robherson.livraria.dto.LivroDTO;
import com.robherson.livraria.exception.LivroAlugadoException;
import com.robherson.livraria.exception.LivroNaoAlugadoException;
import com.robherson.livraria.exception.LivroNotFoundException;
import com.robherson.livraria.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<LivroDTO> findAll(){
        List<Livro> livros =  livroRepository.findAll();

        return livros.stream().map(l -> LivroDTO.fromLivro(l)).collect(Collectors.toList());
    }

    public LivroDTO findById(String id) throws Exception{
        return livroRepository.findById(id)
            .map(l -> LivroDTO.fromLivro(l))
            .orElseThrow(() -> new LivroNotFoundException());
    }

    public List<LivroDTO> searchLivros(LivroDTO livroDto){
        
        Query dynamicQuery = new Query();

        if ( livroDto.getTitulo() != null){
            Criteria nameCriteria = Criteria.where("titulo").regex(livroDto.getTitulo(), "i");
            dynamicQuery.addCriteria(nameCriteria);
        }
        if ( livroDto.getAutor() != null){
            Criteria autorCriteria = Criteria.where("autor").regex(livroDto.getAutor(), "i");
            dynamicQuery.addCriteria(autorCriteria);
        }
        if ( livroDto.getAno() != null){
            Criteria anoCriteria = Criteria.where("ano").regex(livroDto.getAno(), "i");
            dynamicQuery.addCriteria(anoCriteria);
        }
        if ( livroDto.getEstaAlugado() != null){
            Criteria estaAlugadoCriteria = Criteria.where("estaAlugado").is(livroDto.getEstaAlugado());
            dynamicQuery.addCriteria(estaAlugadoCriteria);
        }

        List<Livro> result = mongoTemplate.find(dynamicQuery, Livro.class);

        return result.stream().map(l -> LivroDTO.fromLivro(l)).collect(Collectors.toList());
        
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

    public LivroDTO devolverLivro(String id) throws Exception{
        Livro livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNotFoundException());

        if(!livro.getEstaAlugado()){
            throw new LivroNaoAlugadoException();
        }
        
        livro.setEstaAlugado(false);
        livroRepository.save(livro);

        return LivroDTO.fromLivro(livro);
    }

    public LivroDTO editarLivro(LivroDTO livroDTO) throws Exception{
        Livro livro = livroRepository.findById(livroDTO.getId())
        .orElseThrow(() -> new LivroNotFoundException());

        if (livro.getEstaAlugado()){
            throw new LivroAlugadoException();
        }

        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAno(livroDTO.getAno());
        livro.setEstaAlugado(livroDTO.getEstaAlugado());

        livroRepository.save(livro);
        
        return LivroDTO.fromLivro(livro);
    }

    public LivroDTO removerLivro(String id) throws Exception{
        Livro livro = livroRepository.findById(id)
        .orElseThrow(() -> new LivroNotFoundException());

        if (livro.getEstaAlugado()){
            throw new LivroAlugadoException();
        }

        livroRepository.delete(livro);
        
        return LivroDTO.fromLivro(livro);
    }


    
    
}
