package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoACFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoACFoundException(NoACFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Air Conditioner Not Found");
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(ACAlreadyExistsWithDeviceIdException.class)
    public ResponseEntity<ProblemDetail> handleACAlreadyExistsWithDeviceIdException(ACAlreadyExistsWithDeviceIdException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Duplicate Device ID");
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problem);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemDetail> handleNoResourceFoundException(NoResourceFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, 
                "The requested endpoint was not found on this server.");
        problem.setTitle("Endpoint Not Found");
        problem.setProperty("attemptedPath", ex.getResourcePath()); 
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
}