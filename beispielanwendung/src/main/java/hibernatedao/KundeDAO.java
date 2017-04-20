package hibernatedao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAO extends HibernateDaoSupport implements IKundeDAO {

    public List getByName(String name) {
        return getHibernateTemplate().find(
                "from businessobjects.Kunde kunde where kunde.name=?", name);
    }

    public void deleteByName(String name) {
        getHibernateTemplate().deleteAll(getByName(name));
    }

    public Kunde getByID(int id) {
        return (Kunde) getHibernateTemplate().get(Kunde.class, new Integer(id));
    }

    public void deleteByID(int id) {
        getHibernateTemplate().delete(getByID(id));
    }

    public Kunde save(Kunde kunde) {
        getHibernateTemplate().save(kunde);
        return kunde;
    }

    public void update(Kunde kunde) {
        getHibernateTemplate().update(kunde);
    }

    public List getAll() {
        return getHibernateTemplate().loadAll(Kunde.class);
    }

}