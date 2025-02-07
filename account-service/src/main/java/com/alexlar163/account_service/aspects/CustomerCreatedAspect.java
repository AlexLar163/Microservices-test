package com.alexlar163.account_service.aspects;

import com.alexlar163.account_service.dto.CustomerEventDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerCreatedAspect {

    private static final Logger logger = LoggerFactory.getLogger(CustomerCreatedAspect.class);

    @Before("execution(* com.alexlar163.account_service.services.CreateAccountConsumerService.consumeMessage(..))")
    public void logBeforeConsumeMessage(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof CustomerEventDto message) {
            logger.info("AOP ---> A new Account to be created for Customer ID: {}", message.getCustomerId());
            logger.info("AOP ---> Customer Name: {}", message.getCustomerName());
        }
    }
}