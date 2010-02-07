package axis;

import java.util.List;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import businessobjects.Ware;
import dao.IWareDAO;

public class AxisWareDAO extends ServletEndpointSupport {

    private IWareDAO wareDAO;

    protected void onInit() {
        wareDAO = (IWareDAO) getWebApplicationContext().getBean("wareDAO");
    }

    public void deleteByBezeichnung(String bezeichnung) {
        wareDAO.deleteByBezeichnung(bezeichnung);
    }

    public void deleteByID(int id) {
        wareDAO.deleteByID(id);
    }

    public List getByBezeichnung(String bezeichnung) {
        return wareDAO.getByBezeichnung(bezeichnung);
    }

    public Ware getByID(int id) {
        return wareDAO.getByID(id);
    }

    public Ware save(Ware ware) {
        return wareDAO.save(ware);
    }

    public void update(Ware ware) {
        wareDAO.update(ware);
    }

    
    
}
