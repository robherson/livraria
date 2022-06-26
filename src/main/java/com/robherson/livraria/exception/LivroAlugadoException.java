package com.robherson.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Livro está alugado!")
public class LivroAlugadoException extends RuntimeException{
    
}
