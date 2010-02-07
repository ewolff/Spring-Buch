package jmslistener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receive {

    public static void main(String[] args) {
        System.out.println("JMS POJO Receive");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "jmspojo.xml");
    }

}
