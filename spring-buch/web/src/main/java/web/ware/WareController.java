package web.ware;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import businessobjects.Ware;
import dao.IWareDAO;

@Controller
@RequestMapping("/Ware")
public class WareController {

	private IWareDAO wareDAO;

	@Autowired
	public void setWareDAO(IWareDAO wareDAO) {
		this.wareDAO = wareDAO;
	}

	private String successView = "success";

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	private String wareListeView = "wareListe";

	public String getWareListeView() {
		return wareListeView;
	}

	public void setWareListeView(String wareView) {
		this.wareListeView = wareView;
	}

	@RequestMapping(params = "method=deleteByBezeichnung", method = {RequestMethod.POST, RequestMethod.GET})
	public String deleteByBezeichnung(@RequestParam("name")
	String name) {
		wareDAO.deleteByBezeichnung(name);
		return getSuccessView();
	}

	@RequestMapping(params = "method=deleteByID", method = {RequestMethod.POST, RequestMethod.GET})
	public String deleteByID(@RequestParam("id")
	int id) {
		wareDAO.deleteByID(id);
		return getSuccessView();
	}

	@RequestMapping(params = "method=getByBezeichnung", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getByBezeichnung(@RequestParam("name")
	String name) {
		return new ModelAndView(wareListeView, "wareListe", wareDAO
				.getByBezeichnung(name));
	}

	@RequestMapping(params = "method=getByID", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getByID(@RequestParam("id")
	int id) {
		List<Ware> wareListe = new ArrayList<Ware>();
		wareListe.add(wareDAO.getByID(id));
		return new ModelAndView(wareListeView, "wareListe", wareListe);

	}

	@RequestMapping(params = "method=create", method = {RequestMethod.POST, RequestMethod.GET})
	public String create(@ModelAttribute
	Ware ware) {
		wareDAO.save(ware);
		return getSuccessView();
	}

	@RequestMapping(params = "method=getAll", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView getAll() {
		return new ModelAndView(wareListeView, "wareListe", wareDAO.getAll());
	}

}
