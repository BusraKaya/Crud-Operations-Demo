package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

//    Your @Around advice proceeds, but does not return the result of the proceed() call.
//    Your advice method has a void return type, your intercepted target method has not.
//    So the advice implicitly returns null. By the way, for primitive types like int this would not even work and throw exceptions because of incompatible return types.
//    I was actually surprised that Spring AOP, weirdly enough, even intercepts non-void methods if the around-advice returns void,
//    because AFAIR native AspectJ would not match non-void methods in this case.
//
//    So what can you do?
//
//    Either keep the @Around advice, if you really think you need it.
//    Usually, this is only the case if you want to do more than log things, e.g.
//    modify method parameters or return values, handle exceptions or other things potentially changing the control flow:
//    @Around("execution(* com.example.demo.service.*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("Method called " + joinPoint.getSignature().getName());
//        try {
//            return joinPoint.proceed();
//        }
//        finally {
//            logger.info("Method executed  " + joinPoint.getSignature().getName());
//        }
//    }

//    Or keep it simple and just use a pair of @Before and @After advice methods, if there is no need to transfer data from one advice to the other.
//    This is much simpler, because you do not need to proceed, use try-finally or return anything:

    @Before("execution(* com.example.demo.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("[BEFORE] " + joinPoint);
    }

    @After("execution(* com.example.demo.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("[AFTER] " + joinPoint);
    }
}
