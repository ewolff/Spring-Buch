package test;

import org.springframework.transaction.UnexpectedRollbackException;

import base.SpringTestCase;
import businessobjects.Einkaufswagen;
import businessobjects.Kunde;
import businessobjects.Ware;
import businessprocess.BestellungException;
import businessprocess.EinfacherKreditkartenAutorisierer;
import businessprocess.IBestellungTransaktionBusinessProcess;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

public class BestellungTransaktionTestMitSpring extends SpringTestCase {

    private static final EinfacherKreditkartenAutorisierer ALWAYS_FALSE_KREDITKARTEN_AUTHORISIERER = new EinfacherKreditkartenAutorisierer(
            false);

    private static final EinfacherKreditkartenAutorisierer ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER = new EinfacherKreditkartenAutorisierer(
            true);

    private IKundeDAO kundeDAO;

    private IBestellungDAO bestellungDAO;

    private IWareDAO wareDAO;

    private IBestellungTransaktionBusinessProcess bestellungTransaktion;

    private Kunde kunde;

    private Ware ware;

    private EinkaufswagenVerarbeiter bestellenMitTransactionManager = new EinkaufswagenVerarbeiter() {

        public void doIt(Einkaufswagen einkaufswagen)
                throws BestellungException {
            bestellungTransaktion.bestellenKreditkarteTransactionManager(
                    einkaufswagen, 42);

        }

    };

    private EinkaufswagenVerarbeiter bestellenMitTransactionTemplate = new EinkaufswagenVerarbeiter() {

        public void doIt(Einkaufswagen einkaufswagen)
                throws BestellungException {
            bestellungTransaktion.bestellenKreditkarteTransactionTemplate(
                    einkaufswagen, 42);
        }

    };

    private EinkaufswagenVerarbeiter bestellenMitAnnotations = new EinkaufswagenVerarbeiter() {

        public void doIt(Einkaufswagen einkaufswagen)
                throws BestellungException {
            bestellungTransaktion.bestellenKreditkarteTransactionAnnotation(
                    einkaufswagen, 42);
        }

    };

    public BestellungTransaktionTestMitSpring() {
        super();
        setDependencyCheck(false);
    }

    public void setBestellungTransaktion(
            IBestellungTransaktionBusinessProcess bestellungTransaktion) {
        this.bestellungTransaktion = bestellungTransaktion;
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

    public void testErfolgreicheBestellungKreditkarteTransactionManager()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);

        bestellungKreditkarteTesten(1, 1, bestellenMitTransactionManager);
    }

    public void testErfolgloseBestellungKreditkarteTransactionManager()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_FALSE_KREDITKARTEN_AUTHORISIERER);
        bestellungKreditkarteTesten(0, 1, bestellenMitTransactionManager);
    }

    public void testExceptionBestellungKreditkarteTransactionManager()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);
        try {
            bestellungKreditkarteTesten(0, 3, bestellenMitTransactionManager);
        } catch (BestellungException ex) {
        }
    }

    public void testErfolgreicheBestellungKreditkarteTransactionTemplate()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);
        bestellungKreditkarteTesten(1, 1, bestellenMitTransactionTemplate);
    }

    public void testErfolgloseBestellungKreditkarteTransactionTemplate()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_FALSE_KREDITKARTEN_AUTHORISIERER);
        bestellungKreditkarteTesten(0, 1, bestellenMitTransactionTemplate);
    }

    public void testExceptionBestellungKreditkarteTransactionTemplate()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);
        try {
            bestellungKreditkarteTesten(0, 3, bestellenMitTransactionTemplate);
        } catch (BestellungException ex) {
        }
    }

    public void testErfolgreicheBestellungKreditkarteTransactionAnnotation()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);
        bestellungKreditkarteTesten(1, 1, bestellenMitAnnotations);
    }

    public void testErfolgloseBestellungKreditkarteTransactionAnnotation()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_FALSE_KREDITKARTEN_AUTHORISIERER);
        try {
            bestellungKreditkarteTesten(0, 1, bestellenMitAnnotations);
        } catch (UnexpectedRollbackException ex) {

        }
    }

    public void testExceptionBestellungKreditkarteTransactionAnnotation()
            throws Exception {
        if (bestellungTransaktion == null)
            return;
        bestellungTransaktion
                .setKreditkartenAuthorisierer(ALWAYS_TRUE_KREDITKARTEN_AUTHORISIERER);
        try {
            bestellungKreditkarteTesten(0, 3, bestellenMitAnnotations);
        } catch (UnexpectedRollbackException ex) {

        } catch (BestellungException ex) {

        }
    }

    private void bestellungKreditkarteTesten(int anzahlBestellungenNachTest,
            int anzahlWaren, EinkaufswagenVerarbeiter einkaufswagenVerarbeiter)
            throws BestellungException {
        if (bestellungTransaktion == null)
            return;
        double alterKontostand = kunde.getKontostand();
        Einkaufswagen einkaufswagen = new Einkaufswagen(kunde.getId());
        einkaufswagen.add(ware.getId(), anzahlWaren);
        try {
            einkaufswagenVerarbeiter.doIt(einkaufswagen);
        } catch (UnexpectedRollbackException ex) {
        }
        assertAnzahlBestellungen(anzahlBestellungenNachTest);
        kunde = kundeDAO.getByID(kunde.getId());
        assertEquals(alterKontostand, kunde.getKontostand(), 0.0001);
    }

    protected void onSetUpInTransaction() throws Exception {
        kunde = new Kunde("Eberhard", "Wolff", 42.0);
        kunde = kundeDAO.save(kunde);
        ware = new Ware("iPod", 20);
        ware = wareDAO.save(ware);
        assertAnzahlBestellungen(0);
    }

}