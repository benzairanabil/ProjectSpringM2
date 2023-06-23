package org.cigma.ecom.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingArticleServiceAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* org.cigma.ecom.service.ArticleServiceImp.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("Preparing to execute method {} with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("execution(* org.cigma.ecom.service.ArticleServiceImp.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        logger.info("Successfully executed method {}", joinPoint.getSignature().getName());
    }
    
    @AfterReturning(pointcut = "execution(* org.cigma.ecom.service.ArticleServiceImp.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "execution(* org.cigma.ecom.service.ArticleServiceImp.*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method {} threw an exception", joinPoint.getSignature().getName(), exception);
    }
    
}

