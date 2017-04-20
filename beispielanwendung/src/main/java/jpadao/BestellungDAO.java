package jpadao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import businessobjects.Bestellung;
import dao.IBestellungDAO;

public class BestellungDAO implements IBestellungDAO {

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void deleteByIDKunde(int id_kunde) {
        EntityManager em = EntityManagerFactoryUtils
                .getTransactionalEntityManager(entityManagerFactory);
        em.createQuery("DELETE FROM Bestellung b WHERE b.kunde.id=?1")
                .setParameter(1, id_kunde).executeUpdate();
    }

    public Bestellung save(Bestellung bestellung) {
        EntityManager em = EntityManagerFactoryUtils
                .getTransactionalEntityManager(entityManagerFactory);
        em.persist(bestellung);
        return bestellung;
    }

    public List getByIDKunde(int id_kunde) {
        EntityManager em = EntityManagerFactoryUtils
                .getTransactionalEntityManager(entityManagerFactory);
        return em.createQuery(
                "SELECT b FROM Bestellung b WHERE b.kunde.id=:kundeId")
                .setParameter("kundeId", id_kunde).getResultList();
    }

}