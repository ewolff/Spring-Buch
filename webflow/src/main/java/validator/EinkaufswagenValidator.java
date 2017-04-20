package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import businessobjects.Einkaufswagen;
import dao.IKundeDAO;

public class EinkaufswagenValidator {
	private IKundeDAO kundeDAO;

	public void validateFormularKundenId(Einkaufswagen einkaufswagen, Errors errors) {		
		if (kundeDAO.getByID(einkaufswagen.getId_Kunde()) == null) {
			errors.rejectValue("id_Kunde","kunde.nichtgefunden", "Kunde nicht gefunden!");
		}
	}

	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

}