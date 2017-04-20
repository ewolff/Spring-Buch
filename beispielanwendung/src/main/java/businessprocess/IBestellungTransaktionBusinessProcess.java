package businessprocess;

import businessobjects.Einkaufswagen;

public interface IBestellungTransaktionBusinessProcess extends
        IBestellungBusinessProcess {

    void bestellenKreditkarteTransactionManager(Einkaufswagen einkaufswagen,
            int kreditkartenNummer) throws BestellungException;

    void bestellenKreditkarteTransactionTemplate(
            final Einkaufswagen einkaufswagen, final int kreditkartenNummer)
            throws BestellungException;

    void bestellenKreditkarteTransactionAnnotation(
            final Einkaufswagen einkaufswagen, final int kreditkartenNummer)
            throws BestellungException;

    void setKreditkartenAuthorisierer(KreditkartenAutorisierer authorisierer);

}
