package com.example.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final ObjectMapper objectMapper;
	
	@ExceptionHandler(NoResidentialPlotException.class)
	public ResponseEntity<ProblemDetail> handleNoResidentialPlotException(NoResidentialPlotException ex){
		ProblemDetail problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		problemDetails.setTitle("No residential plot found");
		return new ResponseEntity<ProblemDetail>(problemDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ProblemDetail> handleNoHandlerFoundException(NoHandlerFoundException ex){
		ProblemDetail problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problemDetails.setTitle("Invalid URL");
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
        problem.setProperty("errors", errorMap);
		
		return new ResponseEntity<ProblemDetail>(problem, HttpStatus.BAD_REQUEST);
	}
}