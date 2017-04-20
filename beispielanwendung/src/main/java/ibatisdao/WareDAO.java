package ibatisdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import businessobjects.Ware;
import dao.IWareDAO;

public class WareDAO extends SqlMapClientDaoSupport implements IWareDAO {

    public List getByBezeichnung(String bezeichnung) {
        return getSqlMapClientTemplate().queryForList("getWareByBezeichnung",
                bezeichnung);
    }

    public List getAll() {
        return getSqlMapClientTemplate().queryForList("getWareAll", null);
    }

    public Ware save(Ware ware) {
        getSqlMapClientTemplate().insert("saveWare", ware);
        return ware;
    }

    public void deleteByBezeichnung(String bezeichnung) {
        getSqlMapClientTemplate()
                .delete("deleteWareByBezeichnung", bezeichnung);
    }

    public Ware getByID(int id) {
        return (Ware) getSqlMapClientTemplate().queryForObject("getWareByID",
                new Integer(id));
    }

    public void deleteByID(int id) {
        getSqlMapClientTemplate().delete("deleteWareByID", new Integer(id));
    }

    public void update(Ware ware) {
        getSqlMapClientTemplate().update("updateWare", ware);
    }

}
