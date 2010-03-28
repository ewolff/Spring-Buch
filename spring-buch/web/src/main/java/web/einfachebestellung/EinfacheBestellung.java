package web.einfachebestellung;

import businessobjects.Einkaufswagen;

public class EinfacheBestellung {

    private int id_kunde;

    private int id_ware;

    private int anzahl;

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public int getId_kunde() {
        return id_kunde;
    }

    public void setId_kunde(int id_kunde) {
        this.id_kunde = id_kunde;
    }

    public int getId_ware() {
        return id_ware;
    }

    public void setId_ware(int id_ware) {
        this.id_ware = id_ware;
    }

    public Einkaufswagen toEinkaufswagen() {
        Einkaufswagen result = new Einkaufswagen(id_kunde);
        result.add(getId_ware(), getAnzahl());
        return result;
    }
    
}
