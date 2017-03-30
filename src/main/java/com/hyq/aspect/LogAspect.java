package com.hyq.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by genius on 2017/3/30.
 */
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.hyq.service..*(..))")
    public void aspect(){};

    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("1:"+signature.getName());
        System.out.println("2:"+signature.getDeclaringType().getName());
        System.out.println("3:"+signature.getDeclaringTypeName());
        System.out.println("4:"+signature.getModifiers());
        System.out.println("5:"+joinPoint.getArgs().toString());
        System.out.println("6:"+joinPoint.getTarget().getClass().getName());
        System.out.println("7:"+joinPoint.getKind());
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint){

    }
}
