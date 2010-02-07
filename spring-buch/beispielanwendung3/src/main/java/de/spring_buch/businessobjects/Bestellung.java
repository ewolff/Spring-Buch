package de.spring_buch.businessobjects;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.validation.constraints.NotNull;

import de.spring_buch.framework.BusinessObject;


public class Bestellung extends BusinessObject {

    private Set<BestellPosition> detail = new HashSet<BestellPosition>();

    @NotNull
    private Kunde kunde;

    private int id;

    public Bestellung() {
        super();
    }

    public Bestellung(Set<BestellPosition> detail, Kunde kunde) {
        super();
        this.detail = detail;
        this.kunde = kunde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Set<BestellPosition> getDetail() {
        return detail;
    }

    public void addDetail(BestellPosition detail) {
        this.detail.add(detail);
    }

    public void removeDetail(BestellPosition detail) {
        this.detail.remove(detail);
    }

    public Iterator detailIterator() {
        return detail.iterator();
    }

    public void setDetail(Set<BestellPosition> detail) {
        this.detail = detail;
    }

    public double determineBetrag() {
        double result = 0.0;
        Iterator iterator = detailIterator();
        while (iterator.hasNext()) {
            BestellPosition bestellungDetail = (BestellPosition) iterator
                    .next();
            result += bestellungDetail.determineBetrag();
        }
        return result;
    }
}
