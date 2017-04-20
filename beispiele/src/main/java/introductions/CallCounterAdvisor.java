package introductions;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class CallCounterAdvisor extends DefaultIntroductionAdvisor {

    public CallCounterAdvisor() {
        super(new CallCounterMixin());
    }

}
