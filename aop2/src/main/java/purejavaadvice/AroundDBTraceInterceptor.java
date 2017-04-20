package purejavaadvice;

import org.aspectj.lang.ProceedingJoinPoint;

import annotationadvice.LogInsert;
import businessobjects.Einkaufswagen;

public class AroundDBTraceInterceptor {

    private LogInsert logInsert = new LogInsert();

    public void aroundBestellen(ProceedingJoinPoint proceedingJoinPoint,
            Einkaufswagen e) throws Throwable {

        try {
            proceedingJoinPoint.proceed();
            logInsert.log("Erfolgreiche Bestellung " + e.toString());

        } catch (Throwable ex) {
            logInsert.log("Fehler bei Bestellung" + ex.toString());
            throw ex;
        }
    }


}
