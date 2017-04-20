package springperformance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Manuell implements IDoIt {

    private String name;
    
    protected transient Log defaultLogger = LogFactory.getLog(getClass());
    
    public void doIt() {
        defaultLogger.trace("aaaaaaaaaaaaaaaabaaaEntering ABean.doItLog()");
        defaultLogger.trace("aaaaaaaaaaaaaaaabaaaExiting ABean.doItLog()");
    }

    public String getName() {
        return name;
    }

    public void setName(String beanName) {
        this.name = beanName;
    }
    

    
}
