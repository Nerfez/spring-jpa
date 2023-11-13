package fr.iocean.species.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ChronoAspect {

    private final Logger logger = LoggerFactory.getLogger(ChronoAspect.class);

    @Pointcut("execution(* fr.iocean.species.services..*.*(..))")
    public void serviceMethods() {
    }

    @Around("serviceMethods()")
    public Object logTiming(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = joinPoint.proceed();
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("Temps écoulé de la méthode : " + joinPoint.getSignature().toShortString() + " en " + duration + " ms");
        }
    }
}
