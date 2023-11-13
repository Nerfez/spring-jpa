package fr.iocean.species.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ExceptionAspect {

    private final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("execution(* fr.iocean.species.services..*.*(..))")
    public void serviceMethods() {
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        logger.error("Exception : ", joinPoint.getSignature().toShortString(), e.getMessage());
    }
}
