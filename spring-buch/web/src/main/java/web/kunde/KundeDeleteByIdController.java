package web.kunde;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/DeleteById")
public class KundeDeleteByIdController extends KundeController {

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm() {
		return new ModelAndView("idForm", "id", 0);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String handlePost(@RequestParam("id")
	int id) {
		kundeDAO.deleteByID(id);
		return "success";
	}

}