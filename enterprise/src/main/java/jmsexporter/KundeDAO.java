package jmsexporter;

public class KundeDAO implements IKundeDAO {

	public Kunde getById(int id) {
		Kunde result = new Kunde();
        result.setName("wolff");
        return result;
	}

}
