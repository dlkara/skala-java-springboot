package com.sk.skala.myapp.aspect;

import java.time.format.DateTimeFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class MetricsAnnotationAspect {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Pointcut("@annotation(com.sk.skala.myapp.aspect.Metrics)")
    public void metricsAnnotation() {}

    @Before("metricsAnnotation()")
    public void logControllerStart(JoinPoint joinPoint) {}

    @Around("metricsAnnotation()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {}
}
