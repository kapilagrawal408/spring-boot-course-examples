package com.example.exception;

public class CountryAlreadyExistsException extends RuntimeException {
    public CountryAlreadyExistsException(String isoCode) {
        super("A country with ISO code " + isoCode + " already exists");
    }

    public CountryAlreadyExistsException(String message, boolean custom) {
        super(message);
    }
}