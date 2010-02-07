package businessprocess;

import businessobjects.Einkaufswagen;

public interface IBestellungBusinessProcess {

    void bestellen(Einkaufswagen einkaufswagen) throws BestellungException;

}