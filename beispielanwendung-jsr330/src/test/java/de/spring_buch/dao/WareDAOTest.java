package de.spring_buch.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.spring_buch.base.SpringTestCase;
import de.spring_buch.businessobjects.Ware;
import de.spring_buch.dao.IWareDAO;

public class WareDAOTest extends SpringTestCase {

	@Autowired
    private IWareDAO wareDAO;

    private void checkForIPod(Ware ware) {
        Assert.assertEquals("iPod", ware.getBezeichnung());
        Assert.assertEquals(1.99, ware.getPreis(), 0.001);
    }

    private Ware getEinzigeWareNachBezeichnung(String bezeichnung) {
        List wareByBezeichnung = wareDAO.getByBezeichnung(bezeichnung);
        Assert.assertEquals(1, wareByBezeichnung.size());
        return (Ware) wareByBezeichnung.get(0);
    }

    @Test
    public void testWareDAOBezeichnung() throws Exception {
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        wareDAO.save(new Ware("iPod", 1.99));
        Ware wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod");
        checkForIPod(wareByBezeichnung);
        wareDAO.deleteByBezeichnung("iPod");
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
    }

    @Test
    public void testWareSave() throws Exception {
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod Shuffle").isEmpty());
        wareDAO.save(new Ware("iPod", 1.99));
        Ware wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod");
        wareByBezeichnung.setBezeichnung("iPod Shuffle");
        wareByBezeichnung.setPreis(4.99);
        wareDAO.update(wareByBezeichnung);
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        wareByBezeichnung = getEinzigeWareNachBezeichnung("iPod Shuffle");
        Assert.assertEquals(wareByBezeichnung.getBezeichnung(), "iPod Shuffle");
        Assert.assertEquals(4.99, wareByBezeichnung.getPreis(), 0.001);
        wareDAO.deleteByBezeichnung("iPod Shuffle");
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod Shuffle").isEmpty());
    }

    @Test
    public void testWareDAOID() throws Exception {
        Assert.assertTrue(wareDAO.getByBezeichnung("iPod").isEmpty());
        Ware iPod = new Ware("iPod", 1.99);
        iPod=wareDAO.save(iPod);
        int id = iPod.getId();
        Ware wareById = wareDAO.getByID(id);
        checkForIPod(wareById);
        wareDAO.deleteByID(id);
        Assert.assertNull(wareDAO.getByID(id));
    }

    @Test
    public void testWareDAOGetAll() throws Exception {
        List before = wareDAO.getAll();
        wareDAO.save(new Ware("iPod", 1.99));
        List after = wareDAO.getAll();
        Assert.assertEquals(before.size() + 1, after.size());
        int missing = 0;
        for (Object object : after) {
            if (!before.contains(object)) {
                missing++;
            }
        }
        Assert.assertEquals(1, missing);
    }

    @Test
    public void testWareDAOGetAllAnder() throws Exception {
    	List<Ware> before = wareDAO.getAll();
    	for (Ware ware : before) {
    		wareDAO.deleteByID(ware.getId());
    	}
        wareDAO.save(new Ware("iPod", 1.99));
        List after = wareDAO.getAll();
        Assert.assertEquals(1, after.size());
        Ware ware = (Ware) after.get(0);
        Assert.assertEquals(1.99,ware.getPreis(),0.001);
        Assert.assertEquals("iPod",ware.getBezeichnung());
    }

    
}
