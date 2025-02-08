package dev.alejandro.centralservice.advice;

import dev.alejandro.centralservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.centralservice.exception.AsignaturaNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class AsignaturaControllerAdvice {

    @ExceptionHandler(AsignaturaNotFoundException.class)
    public ResponseEntity<?> handleAsignaturaNotFoundException(AsignaturaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AsignaturaNotCreatedException.class)
    public ResponseEntity<?> handleAsignaturaNotCreatedException(AsignaturaNotCreatedException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
