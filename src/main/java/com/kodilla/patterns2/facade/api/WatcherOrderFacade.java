package com.kodilla.patterns2.facade.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Aspect
@Component
public class WatcherOrderFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherOrderFacade.class);

    @Before(value = "execution(* com.kodilla.patterns2.facade.api.OrderFacade.proccessOrder(..))" +
            "&& args(order, userId) && target(object)")
    public void logEvent(OrderDto order, long userId, Object object) {
        LOGGER.info("Class: " + object.getClass().getName() + ", Args: userId = " + userId);
    }

//            " && args(theNumber) && target(object)")

    @Around("execution(* com.kodilla.patterns2.facade.api.OrderFacade.proccessOrder(..))")
    public Object measureTime(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            long begin = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("Time consumed: " + (end - begin) + "[ms]");
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            throw throwable;
        }
        return result;
    }
}
