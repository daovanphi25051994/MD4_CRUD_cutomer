package com.codegym.concern;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logger {
    public void error() {
        System.out.println("[CMS] ERROR!");
    }

    @AfterThrowing(pointcut = "execution(public * com.codegym.service.customer.CustomerService.*(..))")
    public void log(){
        System.out.println("loi get all customer");
    }

}