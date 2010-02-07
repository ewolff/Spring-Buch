package validator;

import org.springframework.validation.Errors;

import businessobjects.EinkaufswagenInhalt;

import dao.IWareDAO;

public class EinkaufswagenInhaltValidator {

	private IWareDAO wareDAO;

	public void setWareDAO(IWareDAO wareDAO) {
		this.wareDAO = wareDAO;
	}

	public void validateFormularBestellzeile(EinkaufswagenInhalt einkaufswagenInhalt, Errors errors) {
		if (wareDAO.getByID(einkaufswagenInhalt.getId_Ware()) == null) {
			errors.reject("ware.nichtgefunden", "Ware nicht gefunden!");
		}
	}

}
