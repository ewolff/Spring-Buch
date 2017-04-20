package web.kunde;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/FindById")
public class KundeFindByIdController extends KundeController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm() {
		return new ModelAndView("idForm", "id", 0);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handlePost(@RequestParam("id")
	int id) {
		List kundenListe = new ArrayList();
		kundenListe.add(kundeDAO.getByID(id));
		return new ModelAndView("kundenListe", "kundenListe", kundenListe);
	}

}
