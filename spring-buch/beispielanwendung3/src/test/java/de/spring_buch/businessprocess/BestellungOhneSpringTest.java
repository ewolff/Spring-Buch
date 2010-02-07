package de.spring_buch.businessprocess;

import static org.easymock.EasyMock.isA;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import de.spring_buch.businessobjects.Bestellung;
import de.spring_buch.businessobjects.Einkaufswagen;
import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.businessobjects.Ware;
import de.spring_buch.businessprocess.BestellungBusinessProcess;
import de.spring_buch.businessprocess.BestellungException;
import de.spring_buch.dao.IBestellungDAO;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.dao.IWareDAO;

public class BestellungOhneSpringTest {

	private IBestellungDAO bestellungDAOMock;

	private IKundeDAO kundeDAOMock;

	private IWareDAO wareDAOMock;

	private BestellungBusinessProcess bestellung;

	private Kunde kunde;

	private Ware ware;

	@Before
	public void setUp() throws Exception {
		kundeDAOMock = EasyMock.createMock(IKundeDAO.class);
		bestellungDAOMock = EasyMock.createMock(IBestellungDAO.class);
		wareDAOMock = EasyMock.createMock(IWareDAO.class);
		kunde = new Kunde("Eberhard", "Wolff", 42.0);
		kunde.setId(42);
		ware = new Ware("iPod", 20.0);
		bestellung = new BestellungBusinessProcess(bestellungDAOMock,
				kundeDAOMock, wareDAOMock);
	}

	private void initWareMock() {
		EasyMock.expect(wareDAOMock.getByID(ware.getId())).andReturn(ware);
	}

	private void initKundeMock() {
		EasyMock.expect(kundeDAOMock.getByID(42)).andReturn(kunde);
	}

	@Test(expected = BestellungException.class)
	public void testKeinKunde() throws BestellungException {
		initWareMock();
		EasyMock.expect(kundeDAOMock.getByID(-1)).andReturn(null);
		EasyMock.replay(kundeDAOMock);
		Einkaufswagen einkaufswagen = new Einkaufswagen(-1);
		einkaufswagen.add(ware.getId(), 1);
		try {
			bestellung.bestellen(einkaufswagen);
		} finally {
			EasyMock.verify(kundeDAOMock);
		}
	}

	@Test(expected = BestellungException.class)
	public void testLeererEinkaufswagen() throws BestellungException {
		initKundeMock();
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		bestellung.bestellen(einkaufswagen);
	}

	@Test(expected = BestellungException.class)
	public void testZuTeureBestellung() throws BestellungException {
		initKundeMock();
		initWareMock();
		EasyMock.replay(kundeDAOMock, wareDAOMock);
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(ware.getId(), 3);
		try {
			bestellung.bestellen(einkaufswagen);
		} finally {
			EasyMock.verify(kundeDAOMock, wareDAOMock);
		}
	}

	@Test(expected = BestellungException.class)
	public void testKeineWareBestellung() throws BestellungException {
		initKundeMock();
		EasyMock.expect(wareDAOMock.getByID(-1)).andReturn(null);
		EasyMock.replay(kundeDAOMock, wareDAOMock);
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(-1, 1);
		try {
			bestellung.bestellen(einkaufswagen);
		} finally {
			EasyMock.verify(kundeDAOMock, wareDAOMock);
		}
	}

	@Test
	public void testErfolgreicheBestellung() throws Exception {
		EasyMock.expect(kundeDAOMock.getByID(42)).andReturn(kunde);
		Kunde kundeNeu = new Kunde("Eberhard", "Wolff", 22.0);
		kundeNeu.setId(42);
		kundeDAOMock.update(kundeNeu);
		initWareMock();
		EasyMock.expect(bestellungDAOMock.save(isA(Bestellung.class)))
				.andReturn(new Bestellung());
		EasyMock.replay(kundeDAOMock, wareDAOMock, bestellungDAOMock);
		Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
		einkaufswagen.add(ware.getId(), 1);
		bestellung.bestellen(einkaufswagen);
		EasyMock.verify(kundeDAOMock, wareDAOMock, bestellungDAOMock);
	}

}
