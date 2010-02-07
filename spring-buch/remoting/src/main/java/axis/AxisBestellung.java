package axis;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import businessobjects.Einkaufswagen;
import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

public class AxisBestellung extends ServletEndpointSupport implements
        IBestellungBusinessProcess {

    private IBestellungBusinessProcess bestellungBusinessProcess;

    protected void onInit() {
        bestellungBusinessProcess = (IBestellungBusinessProcess) getWebApplicationContext()
                .getBean("bestellung");
    }

    public void bestellen(Einkaufswagen einkaufswagen)
            throws BestellungException {
        bestellungBusinessProcess.bestellen(einkaufswagen);
    }

}