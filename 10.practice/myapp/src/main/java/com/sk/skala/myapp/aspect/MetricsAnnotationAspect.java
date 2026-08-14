package com.sk.skala.myapp.aspect;

import java.time.LocalTime;
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
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            // 실제 Target 메소드 실행
            return joinPoint.proceed();

        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            String time = LocalTime.now().format(TIME_FORMATTER);

            System.out.println(
                    "[" + time + "] END: "
                    + joinPoint.getSignature().toShortString()
                    + " / " + executionTime + "ms"
            );
        }
    }
}
