package aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.DisposableBean;

@Aspect
public class ServiceCallsBuilder implements DisposableBean {

    private List<ProceedingJoinPoint> serviceCalls = new ArrayList<ProceedingJoinPoint>();

    @Around("SystemArchitektur.businessProcessLayer()")
    public Object registerMethod(ProceedingJoinPoint pjp) throws Throwable {
        saveServiceCall(pjp);
        return pjp.proceed();
    }

    private void saveServiceCall(ProceedingJoinPoint pjp) {
        serviceCalls.add(pjp);
    }

    public void destroy() throws Exception {
        for (ProceedingJoinPoint call : serviceCalls) {
            System.out.println(call.toLongString());
        }
    }

}
