package springtimer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TimerMain {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("timer.xml");
    }

}
