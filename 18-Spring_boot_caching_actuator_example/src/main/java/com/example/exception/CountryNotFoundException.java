package com.example.exception;

/**
 * Base class for all "country not found" scenarios.
 * Declared abstract because callers should always throw a specific subtype,
 * never this generic type directly.
 */
public abstract class CountryNotFoundException extends RuntimeException {
    protected CountryNotFoundException(String message) {
        super(message);
    }
}