package springtimer;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AJobDetailBean extends QuartzJobBean {

    private int value;
    
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date()+" AJobDetailBean, value "+value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
