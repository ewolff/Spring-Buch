package axis;

import java.util.List;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class AxisKundeDAO extends ServletEndpointSupport implements IKundeDAO {

    private IKundeDAO kundeDAO;

    protected void onInit() {
        kundeDAO = (IKundeDAO) getWebApplicationContext().getBean("kundeDAO");
    }

    public void deleteByID(int id) {
        kundeDAO.deleteByID(id);
    }

    public void deleteByName(String name) {
        kundeDAO.deleteByName(name);
    }

    public Kunde getByID(int id) {
        return kundeDAO.getByID(id);
    }

    public List getByName(String name) {
        return kundeDAO.getByName(name);
    }

    public Kunde save(Kunde kunde) {
        return kundeDAO.save(kunde);
    }

    public void update(Kunde kunde) {
        kundeDAO.update(kunde);
    }

    public List getAll() {
        return kundeDAO.getAll();
    }

}