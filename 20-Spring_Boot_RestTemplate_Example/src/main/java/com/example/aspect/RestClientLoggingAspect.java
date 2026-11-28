package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RestClientLoggingAspect {
	private static final int MAX_BODY_LENGTH = 500;
	
	@AfterThrowing(
		pointcut = "execution(* com.example.controller..*(..))",
		throwing = "ex"
	)
    public void logControllerException(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();	
        
        if (ex instanceof HttpClientErrorException hce) {
        		log.error("Upstream 4xx | method={} | status={} | body={}",
                method, hce.getStatusCode(), truncate(hce.getResponseBodyAsString()));
        } else if (ex instanceof HttpServerErrorException hse) {
            log.error("Upstream 5xx | method={} | status={} | body={}",
                method, hse.getStatusCode(), truncate(hse.getResponseBodyAsString()));
        } else if (ex instanceof RestClientException rce) {
            log.error("Upstream failure | method={} | type={} | message={}",
                    method, rce.getClass().getSimpleName(), rce.getMessage(), rce);
        } else {
            log.error("Unexpected exception | method={} | type={} | message={}",
            		method, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        }
	}
	
    private String truncate(String body) {
        if (body == null) return null;
        return body.length() > MAX_BODY_LENGTH? body.substring(0, MAX_BODY_LENGTH) + "...[truncated]": body;
    }		
}
