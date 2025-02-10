package dev.alejandro.sedeservice.advice;

import dev.alejandro.sedeservice.exception.GrupoNotCreatedException;
import dev.alejandro.sedeservice.exception.GrupoNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GrupoControllerAdvice {

    @ExceptionHandler(GrupoNotCreatedException.class)
    public ResponseEntity<String> handleGrupoNotCreatedException(GrupoNotCreatedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(GrupoNotFoundException.class)
    public ResponseEntity<String> handleGrupoNotFoundException(GrupoNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
