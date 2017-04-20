package applicationcontextwildcards;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Wildcards {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:jmx*.xml");
        for (String string : applicationContext.getBeanDefinitionNames()) {
            System.out.println(string);
        }
    }

}
