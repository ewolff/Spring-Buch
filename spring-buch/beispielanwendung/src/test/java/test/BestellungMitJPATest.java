package test;

import org.springframework.test.jpa.AbstractJpaTests;

import businessobjects.Einkaufswagen;
import businessobjects.Kunde;
import businessobjects.Ware;
import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;
import junit.framework.TestCase;

public class BestellungMitJPATest extends TestCase {

    private IKundeDAO kundeDAO;

    private IBestellungDAO bestellungDAO;

    private IWareDAO wareDAO;

    private IBestellungBusinessProcess bestellung;

    private Kunde kunde;

    private Ware ware;

    public void setBestellung(IBestellungBusinessProcess bestellung) {
        this.bestellung = bestellung;
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

    private void assertAnzahlBestellungen(int anzahlBestellungen) {
        assertEquals(anzahlBestellungen, bestellungDAO.getByIDKunde(
                kunde.getId()).size());
    }

    public void testHallo()  { 
    }

    public void _testKeinKunde() {
        Einkaufswagen einkaufswagen = new Einkaufswagen(-1);
        try {
            bestellung.bestellen(einkaufswagen);
            fail("Exception erwartet");
        } catch (BestellungException e) {
        }
    }

    public void _testLeererEinkaufswagen() {
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        try {
            bestellung.bestellen(einkaufswagen);
            fail("Exception erwartet");
        } catch (BestellungException e) {
        }
    }

    public void _testZuTeureBestellung() {
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        einkaufswagen.add(ware.getId(), 3);
        try {
            bestellung.bestellen(einkaufswagen);
            fail("Exception erwartet");
        } catch (BestellungException e) {
        }
    }

    public void _testKeineWareBestellung() {
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        einkaufswagen.add(0, 1);

        try {
            bestellung.bestellen(einkaufswagen);
            fail("Exception erwartet");
        } catch (BestellungException ex) {
        }

    }

    public void _testErfolgreicheBestellung() throws Exception {
        double alterKontostand = kunde.getKontostand();
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        einkaufswagen.add(ware.getId(), 1);
        bestellung.bestellen(einkaufswagen);
        assertAnzahlBestellungen(1);
        kunde = kundeDAO.getByID(kunde.getId());
        assertEquals(alterKontostand - ware.getPreis(), kunde.getKontostand(),
                0.0001);
    }

    protected String[] getConfigLocations() { 
        return new String[] { "jpa-beans.xml" };
    }

    protected void onSetUpInTransaction() throws Exception {
        kunde = new Kunde("Eberhard", "Wolff", 42.0);
        kunde = kundeDAO.save(kunde);
        ware = new Ware("iPod", 20);
        ware = wareDAO.save(ware);
        assertAnzahlBestellungen(0);
    }

    protected void onTearDownInTransaction() throws Exception {
        bestellungDAO.deleteByIDKunde(kunde.getId());
        kundeDAO.deleteByID(kunde.getId());
        wareDAO.deleteByID(ware.getId());
    }

}