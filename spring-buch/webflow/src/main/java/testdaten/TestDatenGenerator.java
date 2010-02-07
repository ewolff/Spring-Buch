package testdaten;

import org.springframework.beans.factory.InitializingBean;

import businessobjects.Kunde;
import businessobjects.Ware;

import dao.IKundeDAO;
import dao.IWareDAO;

public class TestDatenGenerator implements InitializingBean {

	private IKundeDAO kundeDAO;
	private IWareDAO wareDAO;
	private Kunde kunde;
	private Ware ware;

	public void afterPropertiesSet() throws Exception {
		this.kunde=kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
		this.ware=wareDAO.save(new Ware("iPod", 1.0));
	}
	
	public Kunde getKunde() {
		return kunde;
	}


	public Ware getWare() {
		return ware;
	}


	public void setKundeDAO(IKundeDAO kundeDAO) {
		this.kundeDAO = kundeDAO;
	}

	public void setWareDAO(IWareDAO wareDAO) {
		this.wareDAO = wareDAO;
	}

}
