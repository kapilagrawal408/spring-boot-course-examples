package com.example.exception;

public class NoCountryForGivenId extends CountryNotFoundException {

    public NoCountryForGivenId(Long id) {
        super("No country found with id: " + id);
    }

    public NoCountryForGivenId(String message) {
        super(message);
    }
}