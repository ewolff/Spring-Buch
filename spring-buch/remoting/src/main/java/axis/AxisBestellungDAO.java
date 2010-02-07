package axis;

import java.util.List;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import businessobjects.Bestellung;
import dao.IBestellungDAO;

public class AxisBestellungDAO extends ServletEndpointSupport implements
        IAxisBestellungDAO {

    private IBestellungDAO bestellungDAO;
    
	protected void onInit() {
		bestellungDAO = (IBestellungDAO) getWebApplicationContext().getBean("bestellungDAO");
	}
    
    public void deleteByIDKunde(int id_kunde) {
        bestellungDAO.deleteByIDKunde(id_kunde);
    }

    public Bestellung save(Bestellung bestellung) {
        return bestellungDAO.save(bestellung);
    }

    public List getByIDKunde(int id_kunde) {
        return bestellungDAO.getByIDKunde(id_kunde);
    }

    public Bestellung[] getByIDKundeAsArray(int id_kunde) {
        List resultAsList = getByIDKunde(id_kunde);
        Bestellung[] result = new Bestellung[resultAsList.size()];
        resultAsList.toArray(result);
        return result;
    }

}
