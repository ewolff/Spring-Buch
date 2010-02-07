package web.kunde;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import businessobjects.Kunde;

@Controller
@RequestMapping("/Create")
public class KundeCreateController extends KundeController {

	private Validator validator = new KundeValidator();

	@RequestMapping(method = RequestMethod.GET)
	public String handleGet(Model model) {
		model.addAttribute("kunde", new Kunde());
		return "kundeForm";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("name","vorname","kontostand");
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handlePost(@ModelAttribute("kunde")
	Kunde kunde, BindingResult bindingResult) {
		validator.validate(kunde, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("kundeForm");
		}
		kunde = kundeDAO.save(kunde);
		return new ModelAndView("kundeCreated", "kundeID", Integer
				.toString(kunde.getId()));
	}
}