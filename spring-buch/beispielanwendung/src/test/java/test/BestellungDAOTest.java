package test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import base.SpringTestCase;
import businessobjects.Bestellung;
import businessobjects.BestellPosition;
import businessobjects.Kunde;
import businessobjects.Ware;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

public class BestellungDAOTest extends SpringTestCase {

    private IKundeDAO kundeDAO;

    private IBestellungDAO bestellungDAO;

    private Kunde kunde;

    private IWareDAO wareDAO;

    private Ware iBook;

    private Ware powerBook;

    protected void onSetUpInTransaction() throws Exception {
        kunde = new Kunde("Eberhard", "Wolff", 42.0);
        kunde = kundeDAO.save(kunde);
        assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId()).size());
    }

    public void testLeereBestellung() {
        Bestellung bestellung = new Bestellung(new TreeSet(), kunde);
        bestellungDAO.save(bestellung);
        assertEquals(1, bestellungDAO.getByIDKunde(kunde.getId()).size());
        bestellungDAO.deleteByIDKunde(kunde.getId());
        assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId()).size());
    }

    public void testBestellung() {
        createWaren();

        Set details = new HashSet();
        details.add(new BestellPosition(powerBook, 10));
        details.add(new BestellPosition(iBook, 5));
        Bestellung bestellung = new Bestellung(details, kunde);

        bestellungDAO.save(bestellung);

        List bestellungList = bestellungDAO.getByIDKunde(kunde.getId());
        assertEquals(1, bestellungList.size());
        Bestellung bestellungResult = (Bestellung) bestellungList.get(0);
        assertEquals(kunde.getId(), bestellungResult.getKunde().getId());
        checkDetails(bestellungResult.detailIterator());

        bestellungDAO.deleteByIDKunde(kunde.getId());
        assertEquals(0, bestellungDAO.getByIDKunde(kunde.getId()).size());
    }

    private void checkDetails(Iterator detailsIterator) {
        boolean powerBookFound = false;
        boolean iBookFound = false;
        int numberOfDetails = 0;
        while (detailsIterator.hasNext()) {
            numberOfDetails++;
            BestellPosition element = (BestellPosition) detailsIterator
                    .next();
            if (element.getWare().getId() == powerBook.getId()) {
                assertEquals(10, element.getAnzahl());
                powerBookFound = true;
            }
            if (element.getWare().getId() == iBook.getId()) {
                assertEquals(5, element.getAnzahl());
                iBookFound = true;
            }

        }
        assertEquals(2, numberOfDetails);
        assertTrue(powerBookFound);
        assertTrue(iBookFound);
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
