package aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect()
public class AspectJBeforeAfterDebugInterceptor {

    private Log log = LogFactory.getLog(AspectJBeforeAfterDebugInterceptor.class);
    
    @Pointcut("execution(* dao.*.*(..))")
    public void debugPointcut() {}
    
    @Before("debugPointcut()")
    public void before(JoinPoint jp) {
        log.trace("Entering "+jp.getStaticPart().toLongString());
    }

    @AfterReturning("debugPointcut()")
    public void afterReturning(JoinPoint jp) {
        log.trace("Exiting "+jp.getStaticPart().toLongString());
    }

    @AfterThrowing(pointcut="debugPointcut()", throwing="ex")    
    public void afterThrowing(JoinPoint jp, Exception ex) {
        log.trace("Exception in "+jp.getStaticPart().toLongString()+" "+ex);
    }

}
