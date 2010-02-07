package hibernatedao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import businessobjects.Bestellung;
import dao.IBestellungDAO;

public class BestellungDAO extends HibernateDaoSupport implements
        IBestellungDAO {

    public void deleteByIDKunde(int id_kunde) {
        Session session = SessionFactoryUtils.getSession(getSessionFactory(),
                false);
        try {
            Iterator bestellungenIter = getByIDKunde(id_kunde).iterator();
            while (bestellungenIter.hasNext()) {
                session.delete(bestellungenIter.next());
            }
        } catch (HibernateException ex) {
            throw SessionFactoryUtils.convertHibernateAccessException(ex);
        } finally {
            SessionFactoryUtils.releaseSession(session, getSessionFactory());
        }
    }

    public Bestellung save(Bestellung bestellung) {
        Session session = SessionFactoryUtils.getSession(getSessionFactory(),
                false);
        try {
            session.save(bestellung);
        } catch (HibernateException ex) {
            throw SessionFactoryUtils.convertHibernateAccessException(ex);
        } finally {
            SessionFactoryUtils.releaseSession(session, getSessionFactory());
        }
        return bestellung;
    }

    public List getByIDKunde(int id_kunde) {
        Session session = SessionFactoryUtils.getSession(getSessionFactory(),
                false);
        try {
            Query query = session
                    .createQuery("from businessobjects.Bestellung bestellung where bestellung.kunde.id=?");
            query.setInteger(0, id_kunde);
            return query.list();
        } catch (HibernateException ex) {
            throw SessionFactoryUtils.convertHibernateAccessException(ex);
        } finally {
            SessionFactoryUtils.releaseSession(session, getSessionFactory());
        }
    }

}