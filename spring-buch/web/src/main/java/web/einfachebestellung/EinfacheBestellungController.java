package web.einfachebestellung;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

public class EinfacheBestellungController extends AbstractWizardFormController {

    private IBestellungBusinessProcess bestellung;

    private String successView;

    private String cancelView;

    private String exceptionView;

    public EinfacheBestellungController() {
        setCommandClass(EinfacheBestellungCommand.class);
        setCommandName("einfacheBestellung");
    }

    public void setBestellung(IBestellungBusinessProcess bestellung) {
        this.bestellung = bestellung;
    }

    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public void setExceptionView(String exceptionView) {
        this.exceptionView = exceptionView;
    }

    protected ModelAndView processFinish(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) {
        EinfacheBestellungCommand einfacheBestellung = (EinfacheBestellungCommand) command;

        try {
            bestellung.bestellen(einfacheBestellung.toEinkaufswagen());
        } catch (BestellungException e) {
            return new ModelAndView(exceptionView, "message", e.getMessage());
        }
        return new ModelAndView(successView);
    }

    protected ModelAndView processCancel(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        return new ModelAndView(cancelView);
    }

}
