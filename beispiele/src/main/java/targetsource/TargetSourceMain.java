package targetsource;

import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TargetSourceMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "targetsource.xml");
        Bean objekt = (Bean) context
                .getBean("swapper");
        objekt.doIt();
        HotSwappableTargetSource swap = (HotSwappableTargetSource) context
                .getBean("hotSwappableTargetSource");
        swap.swap(new Bean("42"));
        objekt.doIt();
    }

}
