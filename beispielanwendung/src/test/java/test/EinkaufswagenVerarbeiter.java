package test;

import businessobjects.Einkaufswagen;
import businessprocess.BestellungException;

public interface EinkaufswagenVerarbeiter {
    void doIt(Einkaufswagen einkaufswagen) throws BestellungException;
}

