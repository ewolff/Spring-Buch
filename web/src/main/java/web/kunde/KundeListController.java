package web.kunde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.IKundeDAO;

public class KundeListController {

	private IKundeDAO kundeDAO;

	private String view = "kundenListe";

	public void setView(String view) {
		this.view = view;
	}

	@Autowired
	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

	@RequestMapping
	protected ModelAndView handleRequestInternal() {
		return new ModelAndView(view, "kundenListe", kundeDAO.getAll());
	}
}