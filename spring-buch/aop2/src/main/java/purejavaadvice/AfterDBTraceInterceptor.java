package purejavaadvice;

import annotationadvice.LogInsert;
import businessobjects.Einkaufswagen;

public class AfterDBTraceInterceptor  {

    private LogInsert logInsert = new LogInsert();

    public void nachBestellen(Einkaufswagen e) {
        System.out.println("Alternative:AfterReturning");
        logInsert.log("Erfolgreiche Bestellung " + e.toString());
    }

    public void nachBestellenException(Exception ex) {
        System.out.println("Alternative:AfterThrowing");
        logInsert.log("Fehler bei Bestellung"+ex.toString());
    }

  
}
