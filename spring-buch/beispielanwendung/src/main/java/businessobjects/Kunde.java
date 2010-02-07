package businessobjects;

import framework.BusinessObject;

public class Kunde extends BusinessObject {

    private String name;

    private String vorname;

    private int id;

    private double kontostand;

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public Kunde() {
        super();
    }

    public Kunde(String vorname, String name, double kontostand) {
        this.name = name;
        this.vorname = vorname;
        this.kontostand = kontostand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void bezahlen(double betrag) {
        this.kontostand-=betrag;
    }
}