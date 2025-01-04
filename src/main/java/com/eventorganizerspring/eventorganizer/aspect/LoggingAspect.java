package com.eventorganizerspring.eventorganizer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Aspect
@Component
@Log
public class LoggingAspect {
@Before("execution (* com.eventorganizerspring.eventorganizer..*.*(..))")
    public void logging(JoinPoint point){
log.info("O método: " + point.getSignature().toShortString() + " está prester a ser chamado");
}
}
