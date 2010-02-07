package test;

import java.util.List;

import base.SpringTestCase;
import businessobjects.Ware;
import dao.IWareDAO;

public class WareDAOTest extends SpringTestCase {

    private IWareDAO wareDAO;

    public void setWareDAO(IWareDAO wareDAO) {
        this.wareDAO = wareDAO;
    }

    private void checkForIPod(Ware ware) {
        assertEquals("iPod", ware.getBezeichnung());
        assertEquals(1.99, ware.getPreis(), 0.001);
    }

    private Ware getEinzigeWareNachBezeichnung(String bezeichnung) {
        List wareByBezeichnung = wareDAO.getByBezeichnung(bezeichnung);
        assertEquals(1, wareByBezeichnung.size());
        return (Ware) wareByBezeichnung.get(0);
    }

    public void testWareDAOBezeichnung() throws Exception {
        assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        wareDAO.save(new Ware("iPod", 1.99));
        Ware wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod");
        checkForIPod(wareByBezeichnung);
        wareDAO.deleteByBezeichnung("iPod");
        assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
    }

    public void testWareSave() throws Exception {
        assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        assertTrue(wareDAO.getByBezeichnung("iPod Shuffle").isEmpty());
        wareDAO.save(new Ware("iPod", 1.99));
        Ware wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod");
        wareByBezeichnung.setBezeichnung("iPod Shuffle");
        wareByBezeichnung.setPreis(4.99);
        wareDAO.update(wareByBezeichnung);
        assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod Shuffle");
        assertEquals(wareByBezeichnung.getBezeichnung(), "iPod Shuffle");
        assertEquals(4.99, wareByBezeichnung.getPreis(), 0.001);
        wareDAO.deleteByBezeichnung("iPod Shuffle");
        assertTrue(wareDAO.getByBezeichnung("iPod Shuffle").isEmpty());
    }

    public void testWareDAOID() throws Exception {
        assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        Ware iPod = new Ware("iPod", 1.99);
        iPod=wareDAO.save(iPod);
        int id = iPod.getId();
        Ware wareById = wareDAO.getByID(id);
        checkForIPod(wareById);
        wareDAO.deleteByID(id);
        assertNull(wareDAO.getByID(id));
    }

    public void testWareDAOGetAll() throws Exception {
        List before = wareDAO.getAll();
        wareDAO.save(new Ware("iPod", 1.99));
        List after = wareDAO.getAll();
        assertEquals(before.size() + 1, after.size());
        int missing = 0;
        for (Object object : after) {
            if (!before.contains(object)) {
                missing++;
            }
        }
        assertEquals(1, missing);
    }

    public void testWareDAOGetAllAnder() throws Exception {
    	List<Ware> before = wareDAO.getAll();
    	for (Ware ware : before) {
    		wareDAO.deleteByID(ware.getId());
    	}
        wareDAO.save(new Ware("iPod", 1.99));
        List after = wareDAO.getAll();
        assertEquals(1, after.size());
        Ware ware = (Ware) after.get(0);
        assertEquals(1.99,ware.getPreis(),0.001);
        assertEquals("iPod",ware.getBezeichnung());
    }

    
}
