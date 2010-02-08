package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.SpringTestCase;
import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAOTest extends SpringTestCase {

	@Autowired
	private IKundeDAO kundeDAO;

	@Test
	public void testKundeDAOName() throws Exception {
		Assert.assertTrue(kundeDAO.getByName("Wolff").isEmpty());
		kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
		Kunde kundeByName = getEinzigenKundenMitNamen("Wolff");
		Assert.assertEquals("Wolff", kundeByName.getName());
		Assert.assertEquals("Eberhard", kundeByName.getVorname());
		Assert.assertEquals(42.0, kundeByName.getKontostand(), 0.001);
		kundeDAO.deleteByName("Wolff");
		Assert.assertTrue(kundeDAO.getByName("Wolff").isEmpty());
	}

	private Kunde getEinzigenKundenMitNamen(String name) {
		List kundeByNameList = kundeDAO.getByName(name);
		Assert.assertEquals(1, kundeByNameList.size());
		Kunde kundeByName = (Kunde) kundeByNameList.get(0);
		return kundeByName;
	}

	@Test
	public void testKundeDAOSave() throws Exception {
		Assert.assertTrue(kundeDAO.getByName("Wolff").isEmpty());
		kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
		Kunde kundeByName = getEinzigenKundenMitNamen("Wolff");
		kundeByName.setName("Johnson");
		kundeByName.setVorname("Rod");
		kundeByName.setKontostand(84.0);
		kundeDAO.update(kundeByName);
		Assert.assertTrue(kundeDAO.getByName("Wolff").isEmpty());
		kundeByName = getEinzigenKundenMitNamen("Johnson");
		Assert.assertEquals("Johnson", kundeByName.getName());
		Assert.assertEquals("Rod", kundeByName.getVorname());
		Assert.assertEquals(84.0, kundeByName.getKontostand(), 0.001);
		kundeDAO.deleteByName("Johnson");
		Assert.assertTrue(kundeDAO.getByName("Johnson").isEmpty());
	}

	@Test
	public void testKundeDAOGetAll() throws Exception {
		List before = kundeDAO.getAll();
		kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
		List after = kundeDAO.getAll();
		Assert.assertEquals(before.size() + 1, after.size());
		int missing = 0;
		for (Object object : after) {
			if (!before.contains(object)) {
				missing++;
			}
		}
		Assert.assertEquals(1, missing);
		kundeDAO.deleteByName("Wolff");
	}

	@Test
	public void testKundeDAOID() throws Exception {
		Assert.assertTrue(kundeDAO.getByName("Wolff").isEmpty());
		Kunde kunde = new Kunde("Eberhard", "Wolff", 42.0);
		kunde = kundeDAO.save(kunde);
		int id = kunde.getId();
		Kunde kundeById = kundeDAO.getByID(id);
		Assert.assertEquals("Wolff", kundeById.getName());
		Assert.assertEquals("Eberhard", kundeById.getVorname());
		Assert.assertEquals(42.0, kundeById.getKontostand(), 0.001);
		kundeDAO.deleteByID(id);
		Assert.assertNull(kundeDAO.getByID(id));
	}
}
