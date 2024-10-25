package ar.unrn.tp.exception;

public class CustomConcurrencyException extends RuntimeException {
    public CustomConcurrencyException(String message) {
        super(message);
    }
}
