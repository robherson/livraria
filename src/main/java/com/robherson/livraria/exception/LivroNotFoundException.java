package com.robherson.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Livro Não encontrado!")
public class LivroNotFoundException extends Exception {
    
}
