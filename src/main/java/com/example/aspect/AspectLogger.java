package com.example.aspect;

import com.example.dto.AirConditionerDTO;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectLogger {

    // Intercepts all methods inside any class in the controller package before their execution begins
    @Before("execution(* com.example.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("[@Before] Received request to execute endpoint: {}", joinPoint.getSignature().getName());
    }

    //Intercepts only the 'add' method in the Service Implementation
    //The 'returning' attribute binds the returned object to the 'result' method parameter
    @AfterReturning(
            pointcut = "execution(* com.example.service.AirConditionerServiceImpl.add(..))",
            returning = "result"
    )
    public void logAfterReturningAdd(JoinPoint joinPoint, AirConditionerDTO result) {
        log.info("[@AfterReturning] Successfully added AC. Database assigned ID: {}", result.getId());
        log.debug("Full returned DTO: {}", result);
    }

    // 3. @AfterThrowing: Intercepts the 'deleteAirConditionById' method
    // The 'throwing' attribute binds the thrown exception to the 'ex' method parameter
    @AfterThrowing(
            pointcut = "execution(* com.example.service.AirConditionerServiceImpl.deleteAirConditionById(..))",
            throwing = "ex"
    )
    public void logAfterThrowingDelete(JoinPoint joinPoint, Exception ex) {
        log.error("[@AfterThrowing] Failed to delete AC in method {}. Reason: {}", 
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    // 4. @After (Finally): Intercepts all methods in the Service package
    // This runs regardless of whether the method returned successfully or threw an exception
    @After("execution(* com.example.service.*.*(..))")
    public void logAfterService(JoinPoint joinPoint) {
        log.info("[@After] Service execution finished for: {}", joinPoint.getSignature().getName());
    }

    // 5. @Around: Intercepts the QBE search method for performance profiling
    // MUST use ProceedingJoinPoint to control exactly when the target method runs
    @Around("execution(* com.example.service.AirConditionerServiceImpl.searchByAnyDetails(..))")
    public Object logAroundSearch(ProceedingJoinPoint pjp) throws Throwable {
        log.info("[@Around - START] Starting performance profiling for {}", pjp.getSignature().getName());
        
        long startTime = System.currentTimeMillis();
        
        // Let the actual Service method execute and capture what it returns
        Object result = pjp.proceed(); 
        
        long endTime = System.currentTimeMillis();
        
        log.info("[@Around - END] {} executed in {} ms", pjp.getSignature().getName(), (endTime - startTime));
        
        // You MUST return the result, otherwise the Controller receives null!
        return result; 
    }
}