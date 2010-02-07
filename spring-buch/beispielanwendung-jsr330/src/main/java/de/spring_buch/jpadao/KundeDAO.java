package de.spring_buch.jpadao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.spring_buch.businessobjects.Kunde;
import de.spring_buch.dao.IKundeDAO;

@Repository
@Transactional
public class KundeDAO implements IKundeDAO {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public List<Kunde> getByName(String name) {
		return em.createQuery("SELECT k FROM Kunde k WHERE k.name=?1")
				.setParameter(1, name).getResultList();
	}

	public void deleteByName(final String name) {
		 em.createQuery(
			"DELETE FROM Kunde kunde WHERE kunde.name=?1")
			.setParameter(1, name).executeUpdate();
	}

	public Kunde getByID(int id) {
		return em.find(Kunde.class, id);
	}

	public void deleteByID(int id) {
		em.remove(getByID(id));
	}

	public Kunde save(Kunde kunde) {
		em.persist(kunde);
		return kunde;
	}

	public void update(Kunde kunde) {
		em.merge(kunde);
	}

	public List getAll() {
		return em.createQuery("SELECT k FROM Kunde k").getResultList();
	}

}