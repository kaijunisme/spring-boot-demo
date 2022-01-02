package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MemberAccountAspect {

	private static final Logger logger = LoggerFactory.getLogger(MemberAccountAspect.class);

	// 設定切入點
	@Pointcut("execution(* com.example.demo.controller.MemberAccountController.*(..))")
	public void pointcut() {
	}
	
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("=====Before advice starts=====");

        logger.info("訪問 /" + joinPoint.getSignature().getName());
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);

        System.out.println("=====Before advice ends=====");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("=====Around advice starts=====");

        long startTime = System.currentTimeMillis();

        // 呼叫proceed() 方法開始執行原方法
        Object result = joinPoint.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        logger.info("訪問 /" + joinPoint.getSignature().getName() + " Time spent: " + spentTime);

        System.err.println("=====Around advice ends=====");

        return result;    	
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("=====After advice starts=====");

        logger.info("訪問 /" + joinPoint.getSignature().getName());
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);

        System.out.println("=====After advice ends=====");
    }
    
    @AfterReturning(pointcut = "pointcut()", returning = "re")
    public void afterReturn(JoinPoint joinPoint, Object re) {
        System.out.println("=====After Returning advice starts=====");

        logger.info("訪問 /" + joinPoint.getSignature().getName());
        logger.info("Return: " + re);
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);

        System.out.println("=====After Returning advice ends=====");    	
    }
    
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        System.err.println("=====After Throwing advice starts=====");

        logger.warn("訪問 /" + joinPoint.getSignature().getName());
        logger.warn(e.getMessage());
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);

        System.err.println("=====After Throwing advice ends=====");    	
    }

}
