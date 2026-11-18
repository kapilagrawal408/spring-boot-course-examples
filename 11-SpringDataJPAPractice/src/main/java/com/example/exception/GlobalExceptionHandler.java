package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleStudentNottFoundException(StudentNotFoundException ex){
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST , ex.getMessage());
		
		//This will put the exception message and stack trace in the log file.
		log.error("", ex);
		
		problemDetail.setTitle("Invalid roll number");
		return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ProblemDetail> handleNoHandlerFoundException(NoHandlerFoundException ex){
		log.error(ex.getMessage());
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		
		//This will put the exception message and stack trace in the log file.
		log.error("", ex);
		
		problem.setTitle("Invalid URL");
		return new ResponseEntity<ProblemDetail>(problem, HttpStatus.BAD_REQUEST);	
	}
}
