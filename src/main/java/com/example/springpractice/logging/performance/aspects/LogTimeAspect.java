package com.example.springpractice.logging.performance.aspects;

import com.example.springpractice.logging.performance.annotations.LogTimeElapsed;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTimeAspect {
    private final Logger logger = LoggerFactory.getLogger(LogTimeAspect.class);

    @Around(value = "@annotation(logTimeElapsed)")
    public Object logTimeElapsed(ProceedingJoinPoint joinPoint, LogTimeElapsed logTimeElapsed) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Long startTime = System.currentTimeMillis();
        Object  result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        logger.info(methodName + " - " + "Time Elapsed: " + (endTime - startTime));
        System.out.println(logTimeElapsed.message());
        return result;
    }
}
