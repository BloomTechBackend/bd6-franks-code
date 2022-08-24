package com.ata.lambdaexpressions.classroom.exception;

/**
 * Thrown when a carton of ice cream cannot be created for some reason
 */
public class CartonCreationFailedException extends RuntimeException {
    private static final long serialVersionUID = -255357133196767291L;

    public CartonCreationFailedException(String message) {
        super(message);
    }

    public CartonCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
