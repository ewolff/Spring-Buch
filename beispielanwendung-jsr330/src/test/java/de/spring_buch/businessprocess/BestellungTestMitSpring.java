package de.spring_buch.businessprocess;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import de.spring_buch.base.SpringTestCase;
import de.spring_buch.businessobjects.Einkaufswagen;
import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.businessobjects.Ware;
import de.spring_buch.businessprocess.BestellungException;
import de.spring_buch.businessprocess.IBestellungBusinessProcess;
import de.spring_buch.dao.IBestellungDAO;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.dao.IWareDAO;

public class BestellungTestMitSpring extends SpringTestCase {

	@Autowired
	private IKundeDAO kundeDAO;
	@Autowired
	private IBestellungDAO bestellungDAO;
	@Autowired
	private IWareDAO wareDAO;
	@Autowired
	private IBestellungBusinessProcess bestellung;

	private Kunde kunde;

	private Ware ware;

	private void assertAnzahlBestellungen(int anzahlBestellungen) {
		Assert.assertEquals(anzahlBestellungen, bestellungDAO.getByIDKunde(
				kunde.getId()).size());
	}

	@Test(expected = BestellungException.class)
	public void testKeinKunde() throws BestellungException {
		Einkaufswagen einkaufswagen = new Einkaufswagen(-1);
		bestellung.bestellen(einkaufswagen);
	}

	@Test(expected = BestellungException.class)
	public void testLeererEinkaufswagen() throws BestellungException {
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		bestellung.bestellen(einkaufswagen);
	}

	@Test(expected = BestellungException.class)
	public void testZuTeureBestellung() throws BestellungException {
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(ware.getId(), 3);
		bestellung.bestellen(einkaufswagen);
	}

	@Test(expected = BestellungException.class)
	public void testKeineWareBestellung() throws BestellungException {
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(0, 1);
		bestellung.bestellen(einkaufswagen);
	}

	@Test
	public void testErfolgreicheBestellung() throws Exception {
		double alterKontostand = kunde.getKontostand();
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(ware.getId(), 1);
		bestellung.bestellen(einkaufswagen);
		assertAnzahlBestellungen(1);
		kunde = kundeDAO.getByID(kunde.getId());
		Assert.assertEquals(alterKontostand - ware.getPreis(), kunde
				.getKontostand(), 0.0001);
	}

	@Before
	public void setUpInTransaction() throws Exception {
		kunde = new Kunde("Eberhard", "Wolff", 42.0);
		kunde = kundeDAO.save(kunde);
		ware = new Ware("iPod", 20);
		ware = wareDAO.save(ware);
		assertAnzahlBestellungen(0);
	}

}