package jpadao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAO extends JpaDaoSupport implements IKundeDAO {

    public List getByName(String name) {
        return getJpaTemplate().find("SELECT k FROM Kunde k WHERE k.name=?1",
                name);
    }

    public void deleteByName(final String name) {
        getJpaTemplate().execute(new JpaCallback() {

            public Object doInJpa(EntityManager entityManager)
                    throws PersistenceException {
                return entityManager.createQuery(
                        "DELETE FROM Kunde kunde WHERE kunde.name=?1").setParameter(1,
                        name).executeUpdate();
            }

        });
    }

    public Kunde getByID(int id) {
        return getJpaTemplate().find(Kunde.class, id);
    }

    public void deleteByID(int id) {
        getJpaTemplate().remove(getByID(id));
    }

    public Kunde save(Kunde kunde) {
        getJpaTemplate().persist(kunde);
        return kunde;
    }

    public void update(Kunde kunde) {
        getJpaTemplate().merge(kunde);
    }

    public List getAll() {
        return getJpaTemplate().find("SELECT k FROM Kunde k");
    }

}