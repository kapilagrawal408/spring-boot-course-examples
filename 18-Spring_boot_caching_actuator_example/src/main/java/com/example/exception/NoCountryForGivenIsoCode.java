package com.example.exception;

public class NoCountryForGivenIsoCode extends CountryNotFoundException {
    public NoCountryForGivenIsoCode(String isoCode) {
        super("No country found with ISO code: " + isoCode);
    }

    public NoCountryForGivenIsoCode(String message, boolean custom) {
        super(message);
    }
}