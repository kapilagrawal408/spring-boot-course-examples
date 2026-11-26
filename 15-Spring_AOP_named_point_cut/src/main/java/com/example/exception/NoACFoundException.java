package com.example.exception;

public class NoACFoundException extends RuntimeException {
    public NoACFoundException(String message) {
        super(message);
    }
}