package dev.alejandro.sedeservice.advice;

import dev.alejandro.sedeservice.exception.PregradoNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class PregradoControllerAdvice {

    @ExceptionHandler(PregradoNotFoundException.class)
    public ResponseEntity<String> handlePregradoNotFoundException(PregradoNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PregradoNotCreatedException.class)
    public ResponseEntity<String> handlePregradoNotCreatedException(PregradoNotCreatedException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
