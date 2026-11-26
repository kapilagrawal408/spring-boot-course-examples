package com.example.exception;

public class ACAlreadyExistsWithDeviceIdException extends RuntimeException {
    public ACAlreadyExistsWithDeviceIdException(String message) {
        super(message);
    }
}