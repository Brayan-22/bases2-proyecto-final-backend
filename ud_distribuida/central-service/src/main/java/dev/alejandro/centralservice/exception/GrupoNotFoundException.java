package dev.alejandro.centralservice.exception;

public class GrupoNotFoundException extends RuntimeException {
    public GrupoNotFoundException(String message) {
        super(message);
    }
}
