package dev.alejandro.centralservice.advice;

import dev.alejandro.centralservice.exception.NominaNotCreatedException;
import dev.alejandro.centralservice.exception.NominaNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Hidden
public class GeneralControllerAdvice {
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<String>> handleValidationException(WebExchangeBindException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(m -> ((DefaultMessageSourceResolvable) m.getArguments()[0]).getDefaultMessage() + " : " +
                        m.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NominaNotCreatedException.class)
    public ResponseEntity<String> handleNominaNotCreatedException(NominaNotCreatedException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NominaNotFoundException.class)
    public ResponseEntity<String> handleNominaNotFoundException(NominaNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
