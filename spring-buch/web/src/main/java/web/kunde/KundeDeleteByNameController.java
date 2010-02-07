package web.kunde;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/DeleteByName")
public class KundeDeleteByNameController extends KundeController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm() {
		return new ModelAndView("nameForm", "name", "");
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String handlePost(@RequestParam("name")
	String name) {
		kundeDAO.deleteByName(name);
		return "success";
	}

}