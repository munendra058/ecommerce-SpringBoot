package com.spring.ecommerce.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // return type ,class-name.method-name()args)
    // @Before("execution(* *.*(..))")
    // @Before("execution(* com.spring.ecommerce.service.ProductService.*(..))")
    // public void logMethodCall() {
    // logger.info("Check Method Exceptions");
    // }

    // Single Method
    // @Before("execution(*
    // com.spring.ecommerce.service.ProductService.getAllProducts(..))")

    // Multiple Methods
    @Before("execution(* com.spring.ecommerce.service.ProductService.getAllProducts(..))  || execution(* com.spring.ecommerce.service.ProductService.findByProduct(..)) ")
    public void logMethodCall(JoinPoint jp) {
        logger.info("Before Method Executes : " + jp.getSignature().getName());
    }

    @After("execution(* com.spring.ecommerce.service.ProductService.getAllProducts(..))  || execution(* com.spring.ecommerce.service.ProductService.findByProduct(..)) ")
    public void logMethodExecuted(JoinPoint jp) {
        logger.info("Method Executed : " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.spring.ecommerce.service.ProductService.getAllProducts(..))  || execution(* com.spring.ecommerce.service.ProductService.findByProduct(..)) ")
    public void logMethodError(JoinPoint jp) {
        logger.info("Method Executed with Error : " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.spring.ecommerce.service.ProductService.getAllProducts(..))  || execution(* com.spring.ecommerce.service.ProductService.findByProduct(..)) ")
    public void logMethodGotSucceed(JoinPoint jp) {
        logger.info("Method Executed with Error : " + jp.getSignature().getName());
    }
}
