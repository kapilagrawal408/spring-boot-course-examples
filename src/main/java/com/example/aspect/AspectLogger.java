package com.example.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AspectLogger {
    /*
    * Pointcut that matches all Spring beans annotated with 
    * @Repository, @Service, and @RestController.
    */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" + " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method signature acts as the Pointcut name. Implementation remains empty.
    }
    
    /*
     * Pointcut that matches all methods in our application's core packages.
     */
    @Pointcut("within(com.example.repository..*)" + " || within(com.example.service..*)" + " || within(com.example.controller..*)")
    public void applicationPackagePointcut() {
        // Method signature acts as the Pointcut name. Implementation remains empty.
    }
    
    /**
     * Advice that logs methods throwing exceptions.
     * We combine both pointcuts using the && operator to ensure we only target 
     * our application's Spring beans.
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = '{}' and exception = '{}'", 
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), 
                e.getCause() != null ? e.getCause() : "NULL", 
                e.getMessage(), e);
    }

    /**
     * Advice that logs when a method is entered and exited.
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
        		joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        
        try {
            Object result = joinPoint.proceed();
            
            log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
            		joinPoint.getSignature().getName(), result);

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()), 
            		joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}