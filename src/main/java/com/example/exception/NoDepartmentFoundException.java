package com.example.exception;

public class NoDepartmentFoundException extends RuntimeException {
	public NoDepartmentFoundException(String message) {
		super(message);
	}
}
