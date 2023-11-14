package fr.iocean.species.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LogMethodAspect {

    private final Logger logger = LoggerFactory.getLogger(LogMethodAspect.class);

    //tout cibler     @Pointcut("execution(* *.*(..))") plusieurs erreurs Cannot invoke "org.apache.commons.logging.Log.isDebugEnabled()" because "this.logger" is null
    //cibler le package @Pointcut("execution(* fr.iocean.species..*Controller.get*(..))")
    @Pointcut("execution(* fr.iocean.species..*Controller.get*(..))")
    public void getMethods() {
    }

    @After("getMethods()")
    public void logAfterGetMethods() {
        logger.info("Une method get vient d'être éxecuté");
    }

    @Before("getMethods()")
    public void logMethodName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Nom de la méthode : " + methodName);
    }
}
