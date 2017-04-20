package web.bestellung;

import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import businessobjects.Einkaufswagen;
import businessobjects.EinkaufswagenInhalt;

public class HinzufuegenBestellzeileAction extends FormAction {

    public Event hinzufuegenBestellzeile(RequestContext context) {
        Einkaufswagen einkaufswagen = (Einkaufswagen) context.getFlowScope()
                .get("einkaufswagen");
        EinkaufswagenInhalt bestellzeile = (EinkaufswagenInhalt) context
                .getFlashScope().get("bestellzeile");
        if (bestellzeile==null) {
        	System.out.println("Bestellzeile ist null");
        }
        einkaufswagen.add(bestellzeile);
        return result("erfolg");
    }

}
