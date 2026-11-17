package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
	private ObjectMapper objectMapper;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> handleInvalidMethodArguments(MethodArgumentNotValidException ex){
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Input validation failed. Please check the provided fields.");
		problem.setTitle("Invalid Input");
		
		Map<String, String> errorMap = new HashMap<>();
		//this code is to convert the error in form of a map
		ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		problem.setProperty("invalid_fields", errorMap);
		
		String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
		log.error("Validation failed! here is the detail:\n{}", prettyJson);
		
		return new ResponseEntity<ProblemDetail>(problem, HttpStatus.BAD_REQUEST);
	}
}