package com.example.exception;

public class NoCompanyFoundException extends ResourceNotFoundException {
    public NoCompanyFoundException(String message) {
        super(message);
    }
}