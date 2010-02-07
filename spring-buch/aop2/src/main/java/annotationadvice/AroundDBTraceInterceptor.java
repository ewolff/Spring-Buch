package annotationadvice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import businessobjects.Einkaufswagen;
import businessprocess.BestellungTest;

@Aspect()
public class AroundDBTraceInterceptor {

    private LogInsert logInsert = new LogInsert();

    @Around(value = "execution(void bestellen(businessobjects.Einkaufswagen)) && args(e)", argNames = "e")
    public void invoke(ProceedingJoinPoint proceedingJoinPoint, Einkaufswagen e)
            throws Throwable {

        try {
            proceedingJoinPoint.proceed();
            logInsert.log("Erfolgreiche Bestellung " + e);

        } catch (Throwable ex) {
            logInsert.log("Fehler bei Bestellung" + ex.toString());
            throw ex;
        }
    }

   

}
