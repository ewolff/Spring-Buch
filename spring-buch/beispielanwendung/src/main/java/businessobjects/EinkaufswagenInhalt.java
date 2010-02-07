package businessobjects;

import framework.BusinessObject;

public class EinkaufswagenInhalt extends BusinessObject {

    private int id_ware;

    private int anzahl;

    public EinkaufswagenInhalt() {
        super();
    }

    public EinkaufswagenInhalt(int id_ware, int anzahl) {
        super();
        this.id_ware = id_ware;
        this.anzahl = anzahl;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public int getId_Ware() {
        return id_ware;
    }

    public void setId_Ware(int id_ware) {
        this.id_ware = id_ware;
    }

}
