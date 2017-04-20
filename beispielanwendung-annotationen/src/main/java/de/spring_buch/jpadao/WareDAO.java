package de.spring_buch.jpadao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.spring_buch.businessobjects.Ware;
import de.spring_buch.dao.IWareDAO;

@Repository
public class WareDAO implements IWareDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public List getByBezeichnung(String bezeichnung) {
        return em.createQuery(
                "SELECT w FROM Ware w WHERE w.bezeichnung=:bezeichnung")
                .setParameter("bezeichnung", bezeichnung).getResultList();
    }

    public Ware save(Ware ware) {
        em.persist(ware);
        return ware;
    }

    public void deleteByBezeichnung(String bezeichnung) {
        em.createQuery("DELETE FROM Ware w WHERE w.bezeichnung=:bezeichnung")
                .setParameter("bezeichnung", bezeichnung).executeUpdate();
    }

    public Ware getByID(int id) {
        return em.find(Ware.class, id);
    }

    public void deleteByID(int id) {
        em.remove(getByID(id));
    }

    public void update(Ware ware) {
        em.merge(ware);

    }

    public List getAll() {
        return em.createQuery("SELECT w FROM Ware w").getResultList();
    }

}
