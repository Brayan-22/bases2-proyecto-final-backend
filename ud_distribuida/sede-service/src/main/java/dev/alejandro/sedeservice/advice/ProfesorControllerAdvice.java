package dev.alejandro.sedeservice.advice;

import dev.alejandro.sedeservice.exception.ProfesorNotCreatedException;
import dev.alejandro.sedeservice.exception.ProfesorNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class ProfesorControllerAdvice {

    @ExceptionHandler(ProfesorNotFoundException.class)
    public ResponseEntity<String> handleProfesorNotFoundException(ProfesorNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProfesorNotCreatedException.class)
    public ResponseEntity<String> handleProfesorNotCreatedException(ProfesorNotCreatedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
