package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitektur {

    @Pointcut("execution (* dao..*+.*(..))")
    public void daoLayer() {
    }

    @Pointcut("execution (* businessprocess..*.*(..))")
    public void businessProcessLayer() {
    }

}
