package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class RestClientExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ProblemDetail> handleHttpClientError(HttpClientErrorException ex) {
        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "Information Unavailable",
                "We encountered an issue fetching this information. Please try again later.");
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ProblemDetail> handleHttpServerError(HttpServerErrorException ex) {
        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "Information Service Unavailable",
                "The information service is temporarily unavailable. Please try again later.");
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ProblemDetail> handleRestClientError(RestClientException ex) {
        return buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Service Unavailable",
                "We could not reach the information service. Please try again later.");
    }

    private ResponseEntity<ProblemDetail> buildResponse(HttpStatus status,
                                                        String title,
                                                        String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        return ResponseEntity.status(status).body(problem);
    }
}