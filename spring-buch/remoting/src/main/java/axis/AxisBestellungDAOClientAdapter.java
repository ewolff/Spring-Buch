package axis;

import java.util.ArrayList;
import java.util.List;

import businessobjects.Bestellung;
import dao.IBestellungDAO;

public class AxisBestellungDAOClientAdapter extends AxisBestellung implements
		IBestellungDAO {

	private IAxisBestellungDAO axisBestellungDAO;

	public void deleteByIDKunde(int id_kunde) {
		axisBestellungDAO.deleteByIDKunde(id_kunde);
	}

	public List getByIDKunde(int id_kunde) {
		Bestellung[] resultAsArray = axisBestellungDAO
				.getByIDKundeAsArray(id_kunde);
		List result = new ArrayList();
		for (int i = 0; i < resultAsArray.length; i++) {
			result.add(resultAsArray[i]);
		}
		return result;
	}

	public Bestellung save(Bestellung bestellung) {
		return axisBestellungDAO.save(bestellung);
	}

	public IAxisBestellungDAO getAxisBestellungDAO() {
		return axisBestellungDAO;
	}

	public void setAxisBestellungDAO(IAxisBestellungDAO axisBestellungDAO) {
		this.axisBestellungDAO = axisBestellungDAO;
	}

}
