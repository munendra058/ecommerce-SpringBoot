package com.spring.ecommerce.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfomanceAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerfomanceAspect.class);

    @Around("execution(* com.spring.ecommerce.service.ProductService.getAllProducts(..))")
    public Object moniterTime(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();

        logger.info("Time Taken : " + (end - start));
        return obj;
    }
}
