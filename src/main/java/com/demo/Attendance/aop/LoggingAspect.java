package com.demo.Attendance.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());


    @Pointcut("execution(* com.demo.Attendance.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.demo.Attendance.serviceImplementation.*.*(..))")
    private void forServiceImplementationPackage(){}

    @Pointcut("execution(* com.demo.Attendance.mapper.*.*(..))")
    private void forMapperPackage(){}

    @Pointcut("execution(* com.demo.Attendance.util.*.*(..))")
    private void forUtilPackage(){}

    @Pointcut("forControllerPackage() || forServiceImplementationPackage() || forMapperPackage() || forUtilPackage()")
    public void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        //display method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        myLogger.info("====> in @Before: calling method: "+theMethod);

        //get the arguments
        Object[] args = joinPoint.getArgs();

        //loop through and displays args
        for (Object temArg : args){
            myLogger.info("====> argument: "+temArg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()",returning = "theResult")
    public void afterReturning(JoinPoint joinPoint,Object theResult){

        // display method we are returning from
        String theMethod = joinPoint.getSignature().toShortString();

        myLogger.info("====> in @AfterReturning: from method: "+theMethod);

        // Check if theResult is null before calling toString()
        if (theResult != null) {
            myLogger.info("====> result: " + theResult.toString());
        } else {
            myLogger.info("====> result: null");
        }
    }

    @AfterThrowing(pointcut = "forAppFlow()",throwing = "theException")
    public void afterThrowing(JoinPoint joinPoint,Throwable theException){

        String theMethod = joinPoint.getSignature().toShortString();

        myLogger.info("===> in @AfterThrow: from method: "+theMethod);
        myLogger.info("===> exception: "+theException);
    }
}
