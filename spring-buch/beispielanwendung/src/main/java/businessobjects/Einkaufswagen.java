package businessobjects;

import java.util.ArrayList;
import java.util.List;

import framework.BusinessObject;

public class Einkaufswagen extends BusinessObject {

    private int id_kunde;

    private List<EinkaufswagenInhalt> inhalt = new ArrayList<EinkaufswagenInhalt>();

    public Einkaufswagen(int id_kunde) {
        super();
        this.id_kunde = id_kunde;
    }

    public Einkaufswagen() {
        super();
    }

    public void add(int id_ware, int anzahl) {
        inhalt.add(new EinkaufswagenInhalt(id_ware, anzahl));
    }

    public void add(EinkaufswagenInhalt zeile) {
        inhalt.add(zeile);
    }

    private int _getAnzahlDetails() {
        if (inhalt == null)
            return 0;
        return inhalt.size();
    }
    
    public List<EinkaufswagenInhalt> getInhalt() {
        return inhalt;
    }
    
    private EinkaufswagenInhalt getZeile(int i) {
        return (EinkaufswagenInhalt) inhalt.get(i);
    }
    
    private void _setInhalt(int i, EinkaufswagenInhalt einkaufswagenInhalt) {
        inhalt.set(i, einkaufswagenInhalt);
    }

    private int getId_Ware(int i) {
        return getZeile(i).getId_Ware();
    }

    public int getId_Kunde() {
        return id_kunde;
    }

    private int _getAnzahl(int i) {
        return getZeile(i).getAnzahl();
    }

    public void setId_Kunde(int id_kunde) {
        this.id_kunde = id_kunde;
    }

    private int[][] _getDetailsAsInts() {
        int size = _getAnzahlDetails();
        int[][] result = new int[size][2];
        for (int i = 0; i < size; i++) {
            result[i][0] = getId_Ware(i);
            result[i][1] = _getAnzahl(i);
        }
        return result;
    }

    private void _setDetailsAsInts(int[][] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            add(intArray[i][0], intArray[i][1]);
        }
    }

    public void setInhalt(List<EinkaufswagenInhalt> inhalt) {
        this.inhalt = inhalt;
    }

}
