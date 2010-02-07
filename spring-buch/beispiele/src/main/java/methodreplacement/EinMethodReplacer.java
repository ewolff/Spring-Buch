package methodreplacement;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class EinMethodReplacer implements MethodReplacer {

    public Object reimplement(Object obj, Method method, Object[] args)
            throws Throwable {
        System.out.println("EinMethodReplacer"+obj+""+method+""+args);
        return null;
    }

}
