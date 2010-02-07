package remotetest;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

import businessobjects.Einkaufswagen;
import businessobjects.Kunde;
import businessobjects.Ware;
import businessprocess.IBestellungBusinessProcess;

public abstract class  RemoteBase extends AbstractDependencyInjectionSpringContextTests {

    private static boolean loggedSpringFile = false;

    private IBestellungBusinessProcess bestellung;

    private Kunde kunde;

    private Ware ware;

    private IKundeDAO kundeDAO;

    private IWareDAO wareDAO;

    private IBestellungDAO bestellungDAO;

    public RemoteBase() {
        super();
        setAutowireMode(AUTOWIRE_BY_NAME);
    }

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

    @Override
    protected void onSetUp() {
        kunde = new Kunde("Eberhard", "Wolff", 42.0);
        kunde = kundeDAO.save(kunde);
        ware = new Ware("iPod", 20);
        ware = wareDAO.save(ware);
        assertAnzahlBestellungen(0);
    }

    @Override
    protected void onTearDown() {
        bestellungDAO.deleteByIDKunde(kunde.getId());
        kundeDAO.deleteByID(kunde.getId());
        wareDAO.deleteByID(ware.getId());
    }

    private void assertAnzahlBestellungen(int anzahlBestellungen) {
        assertEquals(anzahlBestellungen, bestellungDAO.getByIDKunde(
                kunde.getId()).size());
    }

    public void testErfolgreicheBestellung() throws Exception {
        double alterKontostand = kunde.getKontostand();
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        einkaufswagen.add(ware.getId(), 1);
        bestellung.bestellen(einkaufswagen);
        assertAnzahlBestellungen(1);
        kunde = kundeDAO.getByID(kunde.getId());
        assertEquals(alterKontostand - ware.getPreis(), kunde.getKontostand(),
                0.0001);
    }


}
