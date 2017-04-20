package de.spring_buch.businessprocess;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.spring_buch.businessobjects.BestellPosition;
import de.spring_buch.businessobjects.Bestellung;
import de.spring_buch.businessobjects.Einkaufswagen;
import de.spring_buch.businessobjects.EinkaufswagenInhalt;
import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.businessobjects.Ware;
import de.spring_buch.dao.IBestellungDAO;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.dao.IWareDAO;

@Repository
public class BestellungBusinessProcess implements IBestellungBusinessProcess {

	private IBestellungDAO bestellungDAO;

	private IKundeDAO kundeDAO;

	private IWareDAO wareDAO;

	@Inject
	public BestellungBusinessProcess(IBestellungDAO bestellungDAO,
			IKundeDAO kundeDAO, IWareDAO wareDAO) {
		this.bestellungDAO = bestellungDAO;
		this.kundeDAO = kundeDAO;
		this.wareDAO = wareDAO;
	}

	@Transactional
	public void bestellen(Einkaufswagen einkaufswagen)
			throws BestellungException {
		Kunde kunde = kundeDAO.getByID(einkaufswagen.getId_Kunde());
		Bestellung bestellung = bestellungErzeugen(einkaufswagen, kunde);
		bestellungDAO.save(bestellung);
		kunde.bezahlen(bestellung.determineBetrag());
		kundeDAO.update(kunde);
	}

	protected Bestellung bestellungErzeugen(Einkaufswagen einkaufswagen)
			throws BestellungException {
		Kunde kunde = kundeDAO.getByID(einkaufswagen.getId_Kunde());
		return bestellungErzeugen(einkaufswagen, kunde);
	}

	protected Bestellung bestellungErzeugen(Einkaufswagen einkaufswagen,
			Kunde kunde) throws BestellungException {
		if (einkaufswagen.getInhalt().size() == 0) {
			throw new BestellungException("Einkaufswagen leer!" + einkaufswagen);
		}
		if (kunde == null) {
			throw new BestellungException("Kunde nicht vorhanden!");
		}
		Set<BestellPosition> detailSet = new HashSet<BestellPosition>();
		for (EinkaufswagenInhalt inhalt : einkaufswagen.getInhalt()) {
			int wareID = inhalt.getId_Ware();
			Ware ware = wareDAO.getByID(wareID);
			if (ware == null) {
				throw new BestellungException("Ware " + wareID
						+ " nicht vorhanden!");
			}
			detailSet.add(new BestellPosition(ware, inhalt.getAnzahl()));
		}
		Bestellung bestellung = new Bestellung(detailSet, kunde);
		if (bestellung.determineBetrag() > kunde.getKontostand()) {
			throw new BestellungException("Kontostand des Kunde zu niedrig!");
		}
		return bestellung;
	}

}