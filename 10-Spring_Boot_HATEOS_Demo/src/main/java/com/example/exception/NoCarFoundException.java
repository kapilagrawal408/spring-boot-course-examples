package com.example.exception;

//Subclass for Car
public class NoCarFoundException extends ResourceNotFoundException {
	public NoCarFoundException(String message) {
		super(message);
	}
}