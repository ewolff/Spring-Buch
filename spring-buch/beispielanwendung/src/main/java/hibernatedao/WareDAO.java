package hibernatedao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import businessobjects.Ware;
import dao.IWareDAO;

@Repository
public class WareDAO implements IWareDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List getByBezeichnung(String bezeichnung) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from businessobjects.Ware ware where ware.bezeichnung=:bezeichnung")
				.setString("bezeichnung", bezeichnung).list();
	}

	public Ware save(Ware ware) {
		sessionFactory.getCurrentSession().save(ware);
		return ware;
	}

	public void deleteByBezeichnung(String bezeichnung) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"delete businessobjects.Ware ware where ware.bezeichnung=:bezeichnung")
				.setString("bezeichnung", bezeichnung).executeUpdate();
	}

	public Ware getByID(int id) {
		return (Ware) sessionFactory.getCurrentSession().get(Ware.class,
				id);
	}

	public void deleteByID(int id) {
		sessionFactory.getCurrentSession().delete(getByID(id));
	}

	public void update(Ware ware) {
		sessionFactory.getCurrentSession().update(ware);
	}

	public List getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Ware")
				.list();
	}

}
