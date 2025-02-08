package dev.alejandro.centralservice.exception;

public class DictarNotFoundException extends RuntimeException {
    public DictarNotFoundException(String message) {
        super(message);
    }
}
