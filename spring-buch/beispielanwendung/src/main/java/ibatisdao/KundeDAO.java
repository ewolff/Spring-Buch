package ibatisdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAO extends SqlMapClientDaoSupport implements IKundeDAO {

    public List getByName(String name) {
        return getSqlMapClientTemplate().queryForList("getKundeByName", name);
    }

    public void deleteByName(String name) {
        getSqlMapClientTemplate().delete("deleteKundeByName", name);
    }

    public Kunde getByID(int id) {
        return (Kunde) getSqlMapClientTemplate().queryForObject("getKundeByID",
                new Integer(id));
    }

    public void deleteByID(int id) {
        getSqlMapClientTemplate().delete("deleteKundeByID", new Integer(id));
    }

    public Kunde save(Kunde kunde) {
        getSqlMapClientTemplate().insert("saveKunde", kunde);
        return kunde;
    }

    public void update(Kunde kunde) {
        getSqlMapClientTemplate().update("updateKunde", kunde);
    }

    public List getAll() {
        return getSqlMapClientTemplate().queryForList("getAllKunde", null);
    }

}