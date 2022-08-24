package com.ata.lambdaexpressions.classroom.exception;

/**
 * Thrown when a recipe cannot be found based on query parameters.
 */
public class RecipeNotFoundException extends Exception {
    private static final long serialVersionUID = -1816675786064688643L;

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
