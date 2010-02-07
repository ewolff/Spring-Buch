package springmail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "mail.xml");
        DemoMailSender sender = (DemoMailSender) applicationContext
                .getBean("demoMailSender");
        sender.send();
        sender.sendMime();
    }

}
