package web.einfachebestellung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

@Controller
@RequestMapping("/Bestellung")
public class EinfacheBestellungController  {

	private IBestellungBusinessProcess bestellung;

    private String successView="bestellungSuccess";

    private String exceptionView="bestellungException";

    @Autowired
    public void setBestellung(IBestellungBusinessProcess bestellung) {
        this.bestellung = bestellung;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

	@RequestMapping(method = RequestMethod.GET)
	public String handleGet(Model model) {
		model.addAttribute("bestellung", new EinfacheBestellung());
		return "bestellungForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handlePost(@ModelAttribute("bestellung")
			EinfacheBestellung einfacheBestellung, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("bestellungForm");
		}
        try {
            bestellung.bestellen(einfacheBestellung.toEinkaufswagen());
        } catch (BestellungException e) {
            return new ModelAndView(exceptionView, "message", e.getMessage());
        }
        return new ModelAndView(successView);
	}
	
	

}
