package introductions;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class CallCounterIntroduction {

    @DeclareParents(value = "introductions.Bean", defaultImpl = CallCounterMixin.class)
    public static CallCounter callCounter;

    @After("execution(* introductions.Bean.*()) &&" + "this(callCounter)")
    public void incCounter(CallCounter callCounter) {
        callCounter.incCounter();
    }

}
