package jmsexporter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Receive {
    
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("jmsexporter-server.xml");
    }

}
