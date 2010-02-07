package web.bestellung;

import businessobjects.Einkaufswagen;
import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

public class BestellungAufgeben  {

	private IBestellungBusinessProcess bestellung;

	public void setBestellung(IBestellungBusinessProcess bestellung) {
		this.bestellung = bestellung;
	}

	public boolean bestellungAufgeben(Einkaufswagen einkaufswagen) {
		try {
			System.out.println(einkaufswagen);
			bestellung.bestellen(einkaufswagen);
		} catch (BestellungException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
