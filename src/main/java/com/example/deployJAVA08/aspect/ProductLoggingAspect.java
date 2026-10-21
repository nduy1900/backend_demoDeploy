package com.example.deployJAVA08.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ProductLoggingAspect {
    @Before("execution(* com.example.deployJAVA08.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("===========BEFORE==========");
        // Tên class
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Tên class: " + className);

        // Tên phương thức
        String method = joinPoint.getSignature().getName();
        log.info("Tên phương thức được gọi: " + method);

        // Các tham số đầu vào
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("Tham số đầu vào: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.example.deployJAVA08.service..*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        // Tên class
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // Tên phương thức
        String method = joinPoint.getSignature().getName();

        System.out.println("=============AFTER RETURNING============");
        log.info("Tên class: " + className);
        log.info("Tên phương thức: " + method);
        log.info("Kết quả trả về: " + result);
        log.info("Thực thi thành công");
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.deployJAVA08.service..*(..))",
            throwing = "ex"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        // Tên class chứa phương thức
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // Tên phương thức
        String method = joinPoint.getSignature().getName();

        System.out.println("=============AFTER THROWING============");
        ex.printStackTrace();
        log.error("Phương thức xảy ra lỗi: " + method);
        log.error("Thông báo lỗi: " + ex.getMessage());

        log.error("Loại exception: " + ex.getClass().getSimpleName());
    }

    @Around("execution(* com.example.deployJAVA08.service..*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        String method = joinPoint.getSignature().getName();

        System.out.println("=============AROUND============");
        log.info("Method: " + method + "executed in " + (end - start) + " ms");
        return result;
    }

}
