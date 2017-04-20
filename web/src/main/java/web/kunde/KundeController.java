package web.kunde;

import org.springframework.beans.factory.annotation.Autowired;

import dao.IKundeDAO;

public abstract class KundeController {

	protected IKundeDAO kundeDAO;

	@Autowired
	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

}
