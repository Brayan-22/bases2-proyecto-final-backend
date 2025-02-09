package dev.alejandro.sedeservice.advice;

import dev.alejandro.sedeservice.exception.PregradoNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PregreadoControllerAdvice {

    @ExceptionHandler(PregradoNotFoundException.class)
    public ResponseEntity<String> handlePregradoNotFoundException(PregradoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(PregradoNotCreatedException.class)
    public ResponseEntity<String> handlePregradoNotCreatedException(PregradoNotCreatedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
