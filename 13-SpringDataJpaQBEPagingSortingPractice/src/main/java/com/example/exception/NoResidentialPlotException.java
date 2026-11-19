package com.example.exception;

public class NoResidentialPlotException extends RuntimeException {
	public NoResidentialPlotException(String message){
		super(message);
	}
}