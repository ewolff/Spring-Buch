package introductions;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class CallCounterMixin extends DelegatingIntroductionInterceptor
        implements CallCounter {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        counter++;
        return super.invoke(mi);
    }
}
