package de.spring_buch.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.spring_buch.base.SpringTestCase;
import de.spring_buch.businessobjects.BestellPosition;
import de.spring_buch.businessobjects.Bestellung;
import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.businessobjects.Ware;
import de.spring_buch.dao.IBestellungDAO;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.dao.IWareDAO;

public class BestellungDAOTest extends SpringTestCase {

	@Autowired
	private IKundeDAO kundeDAO;

	@Autowired
	private IBestellungDAO bestellungDAO;

	@Autowired
	private IWareDAO wareDAO;

	private Kunde kunde;

	private Ware iBook;

	private Ware powerBook;

	@Before
	public void onSetUpInTransaction() throws Exception {
		kunde = new Kunde("Eberhard", "Wolff", 42.0);
		kunde = kundeDAO.save(kunde);
		Assert
				.assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId())
						.size());
	}

	@Test
	public void testLeereBestellung() {
		Bestellung bestellung = new Bestellung(new TreeSet(), kunde);
		bestellungDAO.save(bestellung);
		Assert
				.assertEquals(1, bestellungDAO.getByIDKunde(kunde.getId())
						.size());
		bestellungDAO.deleteByIDKunde(kunde.getId());
		Assert
				.assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId())
						.size());
	}

	@Test
	public void testBestellung() {
		createWaren();

		Set details = new HashSet();
		details.add(new BestellPosition(powerBook, 10));
		details.add(new BestellPosition(iBook, 5));
		Bestellung bestellung = new Bestellung(details, kunde);

		bestellungDAO.save(bestellung);

		List bestellungList = bestellungDAO.getByIDKunde(kunde.getId());
		Assert.assertEquals(1, bestellungList.size());
		Bestellung bestellungResult = (Bestellung) bestellungList.get(0);
		Assert.assertEquals(kunde.getId(), bestellungResult.getKunde().getId());
		checkDetails(bestellungResult.detailIterator());

		bestellungDAO.deleteByIDKunde(kunde.getId());
		Assert
				.assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId())
						.size());
	}

	private void checkDetails(Iterator detailsIterator) {
		boolean powerBookFound = false;
		boolean iBookFound = false;
		int numberOfDetails = 0;
		while (detailsIterator.hasNext()) {
			numberOfDetails++;
			BestellPosition element = (BestellPosition) detailsIterator.next();
			if (element.getWare().getId() == powerBook.getId()) {
				Assert.assertEquals(10, element.getAnzahl());
				powerBookFound = true;
			}
			if (element.getWare().getId() == iBook.getId()) {
				Assert.assertEquals(5, element.getAnzahl());
				iBookFound = true;
			}

		}
		Assert.assertEquals(2, numberOfDetails);
		Assert.assertTrue(powerBookFound);
		Assert.assertTrue(iBookFound);
	}

	private void createWaren() {
		powerBook = new Ware("PowerBook", 42.0);
		powerBook = wareDAO.save(powerBook);
		iBook = new Ware("iBook", 21.0);
		iBook = wareDAO.save(iBook);
	}

	private void deleteWaren() {
		wareDAO.deleteByID(powerBook.getId());
		wareDAO.deleteByID(iBook.getId());
	}

	public void setBestellungDAO(IBestellungDAO bestellungDAO) {
		this.bestellungDAO = bestellungDAO;
	}

	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

	public void setWareDAO(IWareDAO wareDAO) {
		this.wareDAO = wareDAO;
	}

}
