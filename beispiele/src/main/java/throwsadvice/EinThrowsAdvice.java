package throwsadvice;

import java.lang.reflect.Method;
import java.sql.SQLException;

import org.springframework.aop.ThrowsAdvice;

public class EinThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(SQLException ex) throws Throwable {
    }

    public void afterThrowing(Method m, Object[] args, Object target,
            IllegalArgumentException ex) {
    }

}
