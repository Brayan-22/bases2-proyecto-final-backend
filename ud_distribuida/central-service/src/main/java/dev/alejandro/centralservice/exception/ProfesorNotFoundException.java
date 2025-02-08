package dev.alejandro.centralservice.exception;

public class ProfesorNotFoundException extends RuntimeException {
    public ProfesorNotFoundException(String message) {
        super(message);
    }
}
