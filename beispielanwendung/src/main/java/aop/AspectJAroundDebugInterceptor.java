package aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect()
public class AspectJAroundDebugInterceptor {

    private Log log = LogFactory.getLog(AspectJAroundDebugInterceptor.class);
    
    @Around("execution(* dao.*.*(..))")
    public Object invoke(ProceedingJoinPoint pjp)
            throws Throwable {
        log.trace("Entering "+pjp.getStaticPart().toLongString());
        try {
            return pjp.proceed();            
        } catch (Throwable ex) {
            log.trace("Exception" + ex.toString());
            throw ex;
        } finally {
            log.trace("Exiting "+pjp.getStaticPart().toLongString());
        }
    }

}
