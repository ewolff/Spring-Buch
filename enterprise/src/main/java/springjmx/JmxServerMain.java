package springjmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxServerMain {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("jmx-server.xml");
    }

}
