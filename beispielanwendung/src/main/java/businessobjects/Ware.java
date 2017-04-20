package businessobjects;

import framework.BusinessObject;

public class Ware extends BusinessObject {

    private int id;

    private String bezeichnung;

    private double preis;

    public Ware() {
        super();
    }

    public Ware(String bezeichnung, double preis) {
        super();
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
