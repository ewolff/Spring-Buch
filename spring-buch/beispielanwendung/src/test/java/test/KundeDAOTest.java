package test;

import java.util.List;

import base.SpringTestCase;
import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAOTest extends SpringTestCase {

    private IKundeDAO kundeDAO;

    public void setKundeDAO(IKundeDAO kundeDAO) {
        this.kundeDAO = kundeDAO;
    }

    public void testKundeDAOName() throws Exception {
        assertTrue(kundeDAO.getByName("Wolff").isEmpty());
        kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));       
        Kunde kundeByName = getEinzigenKundenMitNamen("Wolff");
        assertEquals("Wolff", kundeByName.getName());
        assertEquals("Eberhard", kundeByName.getVorname());
        assertEquals(42.0, kundeByName.getKontostand(), 0.001);
        kundeDAO.deleteByName("Wolff");
        assertTrue(kundeDAO.getByName("Wolff").isEmpty()); 
    }

    private Kunde getEinzigenKundenMitNamen(String name) {
        List kundeByNameList = kundeDAO.getByName(name);
        assertEquals(1, kundeByNameList.size());
        Kunde kundeByName = (Kunde)kundeByNameList.get(0);
        return kundeByName;
    }

    public void testKundeDAOSave() throws Exception {
        assertTrue(kundeDAO.getByName("Wolff").isEmpty());
        kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
        Kunde kundeByName = getEinzigenKundenMitNamen("Wolff");
        kundeByName.setName("Johnson");
        kundeByName.setVorname("Rod");
        kundeByName.setKontostand(84.0);
        kundeDAO.update(kundeByName);
        assertTrue(kundeDAO.getByName("Wolff").isEmpty());
        kundeByName = getEinzigenKundenMitNamen("Johnson");
        assertEquals("Johnson", kundeByName.getName());
        assertEquals("Rod", kundeByName.getVorname());
        assertEquals(84.0, kundeByName.getKontostand(), 0.001);
        kundeDAO.deleteByName("Johnson");
        assertTrue(kundeDAO.getByName("Johnson").isEmpty());
    }

    public void testKundeDAOGetAll() throws Exception {
        List before = kundeDAO.getAll();
        kundeDAO.save(new Kunde("Eberhard", "Wolff", 42.0));
        List after = kundeDAO.getAll();
        assertEquals(before.size() + 1, after.size());
        int missing = 0;
        for (Object object : after) {
            if (!before.contains(object)) {
                missing++;
            }
        }
        assertEquals(1, missing);
        kundeDAO.deleteByName("Wolff");
    }

    public void testKundeDAOID() throws Exception {
        assertTrue(kundeDAO.getByName("Wolff").isEmpty());
        Kunde kunde = new Kunde("Eberhard", "Wolff", 42.0);
        kunde = kundeDAO.save(kunde);
        int id = kunde.getId();
        Kunde kundeById = kundeDAO.getByID(id);
        assertEquals("Wolff", kundeById.getName());
        assertEquals("Eberhard", kundeById.getVorname());
        assertEquals(42.0, kundeById.getKontostand(), 0.001);
        kundeDAO.deleteByID(id);
        assertNull(kundeDAO.getByID(id));
    }
}
