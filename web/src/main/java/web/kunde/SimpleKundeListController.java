package web.kunde;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import businessobjects.Kunde;

import dao.IKundeDAO;

@Controller
public class SimpleKundeListController {

	private IKundeDAO kundeDAO;

	@Autowired
	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

	@RequestMapping("/listeEinfach.html")
	protected @ModelAttribute("kundenListe")
	List<Kunde> handleRequestInternal() {
		return kundeDAO.getAll();
	}
}