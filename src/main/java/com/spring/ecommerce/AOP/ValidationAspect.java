package com.spring.ecommerce.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    private static final Logger logger = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.spring.ecommerce.service.ProductService.findByProduct(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {

        if (postId < 0) {
            postId = -postId;
        }
        logger.info("print post id : " + postId);

        Object obj = jp.proceed(new Object[] { postId });
        return obj;

    }
}
