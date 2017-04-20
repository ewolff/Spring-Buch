package web.kunde;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/FindByName")
public class KundeFindByNameController extends KundeController {

	private String view = "kundenListe";

	public void setView(String view) {
		this.view = view;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm() {
		return new ModelAndView("nameForm", "name", "");
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handlePost(@RequestParam("name")
	String name) {
		return new ModelAndView("kundenListe", view, kundeDAO.getByName(name));
	}

}
