package web.kunde;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import businessobjects.Kunde;

public class KundeValidator implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(Kunde.class);
    }

    public void validate(Object obj, Errors errors) {
        Kunde kunde = (Kunde) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vorname",
                "required.vorname", "Vorname muss gesetzt sein");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "required.name", "Name muss gesetzt sein");
        if (kunde.getKontostand() < 0.0) {
            errors.rejectValue("kontostand", "negativ.kontostand",
                    "Kontostand darf nicht negativ sein");
        }
    }

}
