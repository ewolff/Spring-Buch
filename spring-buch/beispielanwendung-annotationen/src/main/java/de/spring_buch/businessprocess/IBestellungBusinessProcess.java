package de.spring_buch.businessprocess;

import de.spring_buch.businessobjects.Einkaufswagen;

public interface IBestellungBusinessProcess {

    void bestellen(Einkaufswagen einkaufswagen) throws BestellungException;

}