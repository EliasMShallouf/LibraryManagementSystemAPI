package com.eliasshallouf.examples.library_management_system;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(AppLoggingAspect.class);

    @Around("execution(* com.eliasshallouf.examples.library_management_system.service.db.*.*(..))")
    public Object measureDataBaseCallsExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = className + "." + joinPoint.getSignature().getName() + "()";

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            logger.info("{} has executed in {} ms", methodName, endTime - startTime);

            return result;
        } catch (Exception e) {
            logger.error("Exception in {} : {}", methodName, e.getMessage());
            throw e;
        }
    }
}