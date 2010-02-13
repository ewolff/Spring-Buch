package remotetest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import businessobjects.Einkaufswagen;
import businessobjects.Kunde;
import businessobjects.Ware;
import businessprocess.IBestellungBusinessProcess;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class RemoteBase {

	@Autowired
	private IBestellungBusinessProcess bestellung;

	private Kunde kunde;

	private Ware ware;

	@Autowired
	private IKundeDAO kundeDAO;

	@Autowired
	private IWareDAO wareDAO;

	@Autowired
	private IBestellungDAO bestellungDAO;

	@Before
	public void onSetUp() {
		kunde = new Kunde("Eberhard", "Wolff", 42.0);
		kunde = kundeDAO.save(kunde);
		ware = new Ware("iPod", 20);
		ware = wareDAO.save(ware);
		assertAnzahlBestellungen(0);
	}

	@After
	public void onTearDown() {
		bestellungDAO.deleteByIDKunde(kunde.getId());
		kundeDAO.deleteByID(kunde.getId());
		wareDAO.deleteByID(ware.getId());
	}

	private void assertAnzahlBestellungen(int anzahlBestellungen) {
		Assert.assertEquals(anzahlBestellungen, bestellungDAO.getByIDKunde(
				kunde.getId()).size());
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

}
