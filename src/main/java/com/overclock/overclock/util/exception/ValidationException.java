package com.overclock.overclock.util.exception;

public class ValidationException extends RuntimeException {
    private Object invalidObject;

    public ValidationException(String message, Object invalidObject) {
        this(message);
        this.invalidObject = invalidObject;
    }

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ValidationException: invalid object - " + invalidObject;
    }
}
