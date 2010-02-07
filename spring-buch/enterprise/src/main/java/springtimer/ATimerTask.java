package springtimer;

import java.util.Date;
import java.util.TimerTask;

public class ATimerTask extends TimerTask {

    public void run() {
        System.out.println(new Date()+" ATimerTask");
    }

}
