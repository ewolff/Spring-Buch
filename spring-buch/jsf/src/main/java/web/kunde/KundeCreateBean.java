package web.kunde;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import dao.IKundeDAO;

import businessobjects.Kunde;

public class KundeCreateBean  {
    
    private IKundeDAO kundeDAO;

    private Kunde kunde=new Kunde("","",0.0);
    
    public void validateKontostand(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        if (value != null) {
            try {
                double converted = Double.parseDouble(value.toString());

                if (converted < 0.0) {
                    throw new ValidatorException(new FacesMessage(
                            "Kontostand darf nicht negativ sein!"));
                }
            } catch (NumberFormatException e) {
                throw new ValidatorException(new FacesMessage(
                        "Kontostand muss eine Zahl sein!"));
            }
        }

    }

    public String submit() {
        kundeDAO.save(kunde);
        return "success";
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public IKundeDAO getKundeDAO() {
        return kundeDAO;
    }

    public void setKundeDAO(IKundeDAO kundeDAO) {
        this.kundeDAO = kundeDAO;
    }
    

}
