package de.spring_buch.rest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelMessage.Kind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriTemplate;

import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.dao.IKundeDAO;
import de.spring_buch.jpadao.KundeDAO;

@Controller
public class KundeController {

	private IKundeDAO kundeDAO;

	private Validator validator;

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

	
	@RequestMapping(value = "/kunden", method = RequestMethod.GET)
	public ModelAndView getAll() {
		return new ModelAndView("kundenListe", "kundenListe", kundeDAO.getAll());
	}

	@RequestMapping(value = "/kunden/{id}", method = RequestMethod.GET)
	public ModelAndView getById(@PathVariable("id") int id) {
		Kunde kunde = kundeDAO.getByID(id);
		return new ModelAndView("kunde", "kunde", kunde);
	}

	@RequestMapping(value = "/kunden/{id}", method = RequestMethod.DELETE)
	public String deleteById(@PathVariable int id) {
		kundeDAO.deleteByID(id);
		return "redirect:/springbuchweb/kunden.html";
	}

	@RequestMapping(value = "/kunden", method = RequestMethod.POST)
	public String create(@RequestParam("vorname") String vorname,
			@RequestParam("name") String name,
			@RequestParam("kontostand") double kontostand) {
		Kunde kunde = new Kunde(vorname, name, kontostand);
		Set<ConstraintViolation<Kunde>> violations = validator.validate(kunde);
		if (violations.isEmpty()) {
			kundeDAO.save(kunde);
			return "redirect:kunden/" + kunde.getId() + ".html";
		} else {
			return "redirect:/kundeInvalid.html";
		}
	}

	@RequestMapping(value = "/kunden/{id}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable("id") int id,
			@RequestParam("vorname") String vorname,
			@RequestParam("name") String name,
			@RequestParam("kontostand") double kontostand) {
		Kunde kunde = kundeDAO.getByID(id);
		if (!vorname.equals(kunde.getVorname())) {
			kunde.setVorname(vorname);
		}
		if (!name.equals(kunde.getName())) {
			kunde.setName(name);
		}
		if (kontostand != kunde.getKontostand()) {
			kunde.setKontostand(kontostand);
		}
		kundeDAO.update(kunde);
		return new ModelAndView("kunde", "kunde", kunde);
	}

}
