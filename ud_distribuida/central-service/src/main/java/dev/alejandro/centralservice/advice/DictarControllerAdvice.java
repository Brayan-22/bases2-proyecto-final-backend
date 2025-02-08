package dev.alejandro.centralservice.advice;

import dev.alejandro.centralservice.exception.DictarNotCreatedException;
import dev.alejandro.centralservice.exception.DictarNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class DictarControllerAdvice {

    @ExceptionHandler(DictarNotCreatedException.class)
    public ResponseEntity<String> handleDictarNotCreatedException(DictarNotCreatedException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DictarNotFoundException.class)
    public ResponseEntity<String> handleDictarNotFoundException(DictarNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
