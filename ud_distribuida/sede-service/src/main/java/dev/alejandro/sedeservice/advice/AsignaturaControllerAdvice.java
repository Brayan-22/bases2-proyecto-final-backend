package dev.alejandro.sedeservice.advice;

import dev.alejandro.sedeservice.exception.AsignaturaNotCreatedException;
import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class AsignaturaControllerAdvice {
    @ExceptionHandler(AsignaturaNotCreatedException.class)
    public ResponseEntity<String> handleAsignaturaNotCreatedException(AsignaturaNotCreatedException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
