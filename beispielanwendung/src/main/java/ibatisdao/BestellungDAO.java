package ibatisdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import businessobjects.Bestellung;
import businessobjects.BestellPosition;
import businessobjects.Kunde;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

public class BestellungDAO extends SqlMapClientDaoSupport implements
        IBestellungDAO {

    private IWareDAO wareDAO;

    private IKundeDAO kundeDAO;

    public IKundeDAO getKundeDAO() {
        return kundeDAO;
    }

    public void setKundeDAO(IKundeDAO kundeDAO) {
        this.kundeDAO = kundeDAO;
    }

    public IWareDAO getWareDAO() {
        return wareDAO;
    }

    public void setWareDAO(IWareDAO wareDAO) {
        this.wareDAO = wareDAO;
    }

    public void deleteByIDKunde(int id_kunde) {
        getSqlMapClientTemplate().delete("deleteBestellungDetailByIDKunde",
                new Integer(id_kunde));
        getSqlMapClientTemplate().delete("deleteBestellungByIDKunde",
                new Integer(id_kunde));
    }

    public Bestellung save(Bestellung bestellung) {
        getSqlMapClientTemplate().insert("saveBestellung", bestellung);

        Iterator iterator = bestellung.detailIterator();
        while (iterator.hasNext()) {
            BestellPosition detail = (BestellPosition) iterator.next();
            Map params = new HashMap();
            params.put("id_bestellung", new Integer(bestellung.getId()));
            params.put("anzahl", new Integer(detail.getAnzahl()));
            params.put("id_ware", new Integer(detail.getWare().getId()));
            getSqlMapClientTemplate().insert("saveBestellungDetail", params);
        }
        return bestellung;
    }

    public List getByIDKunde(int id_kunde) {
        List result = new ArrayList();
        List bestellungIDs = getSqlMapClientTemplate().queryForList(
                "getBestellungByIDKunde", new Integer(id_kunde));
        Kunde kunde = kundeDAO.getByID(id_kunde);
        Iterator bestellungIDsIterator = bestellungIDs.iterator();
        while (bestellungIDsIterator.hasNext()) {
            Integer id = (Integer) bestellungIDsIterator.next();
            List detailIDs = getSqlMapClientTemplate().queryForList(
                    "getBestellungDetailByIDBestellung", id);
            Set detailsAsSet = new HashSet();
            Iterator detailIDsIterator = detailIDs.iterator();
            while (detailIDsIterator.hasNext()) {
                Map detailMap = (Map) detailIDsIterator.next();
                int id_ware = ((Integer) detailMap.get("ID_WARE")).intValue();
                int anzahl = ((Integer) detailMap.get("ANZAHL")).intValue();
                detailsAsSet.add(new BestellPosition(wareDAO
                        .getByID(id_ware), anzahl));
            }
            result.add(new Bestellung(detailsAsSet, kunde));
        }
        return result;
    }

}
