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

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
	private ObjectMapper objectMapper;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> getMethodArgumentErrorDetails(MethodArgumentNotValidException ex){
		ProblemDetail problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Object validation failed. Please check the payload.");
		problemDetails.setTitle("Invalid Request Body");
        
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
        log.error("Validation failed! here is the detail:\n{}", prettyJson);
        
        problemDetails.setProperty("invalid_fields", errorMap);
		return new ResponseEntity<ProblemDetail>(problemDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ProblemDetail> getConstraintViolationErrorDetails(ConstraintViolationException ex){
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                "URL parameter validation failed."
        );
        problem.setTitle("Invalid URL Parameter");
        
        Map<String, String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String path = violation.getPropertyPath().toString();
            String paramName = path.substring(path.lastIndexOf('.') + 1); 
            errorMap.put(paramName, violation.getMessage());
        });
        
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorMap);
        log.error("Validation failed! here is the detail:\n{}", prettyJson);
        
        problem.setProperty("invalid_params", errorMap);
		return new ResponseEntity<ProblemDetail>(problem, HttpStatus.BAD_REQUEST);
	}
}
