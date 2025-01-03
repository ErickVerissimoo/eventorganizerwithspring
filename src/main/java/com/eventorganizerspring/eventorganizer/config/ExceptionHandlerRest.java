package com.eventorganizerspring.eventorganizer.config;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerRest {
    @ExceptionHandler(exception = EntityNotFoundException.class)
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public Map<String, String> EntityNotFound(EntityNotFoundException ex) {
    return Map.of("message", "Entidade não encontrada - " + (ex.getLocalizedMessage()!=null? ex.getLocalizedMessage() : "erro desconhecido"));
}



}
