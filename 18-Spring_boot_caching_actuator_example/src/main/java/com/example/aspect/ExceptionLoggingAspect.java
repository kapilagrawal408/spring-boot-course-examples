package com.example.aspect;

import com.example.exception.CountryAlreadyExistsException;
import com.example.exception.CountryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

    /**
     * Logs every exception thrown by any method in the service or controller package.
     * Runs AFTER the exception is thrown, before it propagates to the caller.
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.cachedemo.service..*(..)) "
                 + "|| execution(* com.example.cachedemo.controller..*(..))",
        throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex) {

        String methodName = joinPoint.getSignature().toShortString();

        if (ex instanceof CountryNotFoundException
                || ex instanceof CountryAlreadyExistsException) {
            // Expected business exceptions — client's fault
            log.error("Business exception in {}: {}", methodName, ex.getMessage());
        } else {
            // Unexpected exception — server's fault
            log.error("Unexpected exception in {}: {}", methodName, ex.getMessage(), ex);
        }
    }
}