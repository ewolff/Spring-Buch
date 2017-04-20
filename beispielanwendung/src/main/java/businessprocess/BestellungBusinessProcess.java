package businessprocess;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import businessobjects.BestellPosition;
import businessobjects.Bestellung;
import businessobjects.Einkaufswagen;
import businessobjects.EinkaufswagenInhalt;
import businessobjects.Kunde;
import businessobjects.Ware;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

public class BestellungBusinessProcess implements IBestellungBusinessProcess {

    private IBestellungDAO bestellungDAO;

    private IKundeDAO kundeDAO;

    private IWareDAO wareDAO;

    public BestellungBusinessProcess() {

    }

    public BestellungBusinessProcess(IBestellungDAO bestellungDAO,
            IKundeDAO kundeDAO, IWareDAO wareDAO) {
        this.bestellungDAO = bestellungDAO;
        this.kundeDAO = kundeDAO;
        this.wareDAO = wareDAO;
    }

    @Required
    public void setKundeDAO(IKundeDAO kundeDAO) {
        this.kundeDAO = kundeDAO;
    }

    @Required
    public void setBestellungDAO(IBestellungDAO bestellungDAO) {
        this.bestellungDAO = bestellungDAO;
    }

    @Required
    public void setWareDAO(IWareDAO wareDAO) {
        this.wareDAO = wareDAO;
    }

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

    protected IBestellungDAO getBestellungDAO() {
        return bestellungDAO;
    }

}