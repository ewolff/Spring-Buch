package aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RuntimeExceptionLogger {

    Log log = LogFactory.getLog(RuntimeExceptionLogger.class);

    private boolean activated = true;

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @AfterThrowing(value = "SystemArchitektur.businessProcessLayer()", throwing = "ex")
    public void logException(RuntimeException ex) {
        if (activated) {
            log.error("Exception in Business Process Layer", ex);
        }
    }

}
