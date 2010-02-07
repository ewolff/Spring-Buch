package businessprocess;

import businessobjects.Einkaufswagen;

public class BestellungBusinessProcess {

    public void bestellen(Einkaufswagen einkaufswagen)
            throws BestellungException {
        if (einkaufswagen == null) {
            throw new BestellungException();
        }
    }

}