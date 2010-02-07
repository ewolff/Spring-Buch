package de.spring_buch.jpadao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import de.spring_buch.businessobjects.Bestellung;
import de.spring_buch.dao.IBestellungDAO;

@Repository
public class BestellungDAO implements IBestellungDAO {

	@PersistenceContext
	private EntityManager em;

	public void deleteByIDKunde(int id_kunde) {
		List<Bestellung> list = getByIDKunde(id_kunde);
		for (Bestellung bestellung : list) {
			em.remove(bestellung);
		}
	}

	public Bestellung save(Bestellung bestellung) {
		em.persist(bestellung);
		return bestellung;
	}

	public List getByIDKunde(int id_kunde) {
		return em.createQuery(
				"SELECT b FROM Bestellung b WHERE b.kunde.id=:kundeId")
				.setParameter("kundeId", id_kunde).getResultList();
	}

}