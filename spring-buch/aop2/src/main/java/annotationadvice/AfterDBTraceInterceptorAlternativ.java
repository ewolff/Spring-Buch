package annotationadvice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import businessobjects.Einkaufswagen;
import businessprocess.BestellungTest;

@Aspect
public class AfterDBTraceInterceptorAlternativ {

    private LogInsert logInsert = new LogInsert();

    @Pointcut("execution(void bestellen(..))")
    public void bestellen() {
    }

    @Before(value = "bestellen()  && args(e)", argNames="e")
    public void vorBestellen(Einkaufswagen e) {
        logInsert.log("Bestellung angestossen " + e);
    }

    
    @AfterReturning(value = "bestellen()  && args(e)", argNames = "e")
    public void nachBestellen(Einkaufswagen e) {
        logInsert.log("Erfolgreiche Bestellung " + e);
    }

    @AfterThrowing(value = "bestellen() ", throwing = "ex")
    public void nachBestellenException(Exception ex) {
        logInsert.log("Fehler bei Bestellung" + ex.toString());
    }

    public static void main(String[] args) throws Exception {
        BestellungTest.bestellungTesten("afterannotationadvice.xml");
    }

}
