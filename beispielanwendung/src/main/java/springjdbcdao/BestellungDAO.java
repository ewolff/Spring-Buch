package springjdbcdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlUpdate;

import businessobjects.Bestellung;
import businessobjects.BestellPosition;
import dao.IBestellungDAO;
import dao.IKundeDAO;
import dao.IWareDAO;

class BestellungDetailInsert extends SqlUpdate {

    public BestellungDetailInsert(DataSource ds) {
        super(
                ds,
                "INSERT INTO BESTELLUNGDETAIL(ID_BESTELLUNG, ANZAHL, ID_WARE) VALUES(?,?,?)",
                new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER });
    }

    public void update(int id_bestellung, int anzahl, int id_ware) {
        update(new Object[] { new Integer(id_bestellung), new Integer(anzahl),
                new Integer(id_ware) });
    }
}

public class BestellungDAO extends JdbcDaoSupport implements IBestellungDAO {

    private class BestellungSelect extends MappingSqlQuery<Bestellung> {

        public BestellungSelect(DataSource ds) {
            super(ds, "SELECT ID, ID_KUNDE FROM BESTELLUNG WHERE ID_KUNDE=?");
            declareParameter(new SqlParameter("id", Types.INTEGER));
        }

        protected Bestellung mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bestellung result = new Bestellung();
            result.setId(rs.getInt(1));
            result.setKunde(kundeDAO.getByID(rs.getInt(2)));
            return result;
        }

    }

    private class BestellungDetailSelect extends MappingSqlQuery<BestellPosition> {

        public BestellungDetailSelect(DataSource ds) {
            super(ds,
                    "SELECT ID, ANZAHL, ID_WARE FROM BESTELLUNGDETAIL WHERE ID_BESTELLUNG=?");
            declareParameter(new SqlParameter("id", Types.INTEGER));
        }

        protected BestellPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            BestellPosition result = new BestellPosition();
            result.setId(rs.getInt(1));
            result.setAnzahl(rs.getInt(2));
            result.setWare(wareDAO.getByID(rs.getInt(3)));
            return result;
        }

    }

    
    
    private SqlUpdate bestellungInsert = null;

    private SqlFunction pkQuery = null;

    private SqlUpdate bestellungDelete = null;

    private SqlUpdate bestellungDetailDelete = null;

    private BestellungSelect bestellungSelect;

    private BestellungDetailInsert bestellungDetailInsert;

    private BestellungDetailSelect bestellungDetailSelect;

    private IKundeDAO kundeDAO;

    private IWareDAO wareDAO;

    public BestellungDAO() {
        super();
    }
    
    public BestellungDAO(DataSource dataSource,  IKundeDAO kundeDAO, IWareDAO wareDAO) {
        setDataSource(dataSource);
        this.kundeDAO = kundeDAO;
        this.wareDAO = wareDAO;
    }

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

    public void initDao() throws Exception {
        bestellungInsert = new SqlUpdate(getDataSource(),
                "INSERT INTO BESTELLUNG(ID_KUNDE) VALUES(?)",
                new int[] { Types.INTEGER });
        bestellungDetailInsert = new BestellungDetailInsert(getDataSource());
        bestellungDetailDelete = new SqlUpdate(
                getDataSource(),
                "DELETE FROM BESTELLUNGDETAIL where ID_BESTELLUNG in (SELECT id FROM BESTELLUNG WHERE ID_KUNDE=?)",
                new int[] { Types.INTEGER });
        bestellungDelete = new SqlUpdate(getDataSource(),
                "DELETE FROM BESTELLUNG b WHERE b.ID_KUNDE=?",
                new int[] { Types.INTEGER });
        bestellungSelect = new BestellungSelect(getDataSource());
        bestellungDetailSelect = new BestellungDetailSelect(getDataSource());
        pkQuery = new SqlFunction<Integer>(getDataSource(), "call identity()");
    }

    public void deleteByIDKunde(int id_kunde) {
        bestellungDetailDelete.update(id_kunde);
        bestellungDelete.update(id_kunde);
    }

    public Bestellung save(Bestellung bestellung) {
        bestellungInsert.update(bestellung.getKunde().getId());
        bestellung.setId(pkQuery.run());
        Iterator iterator = bestellung.detailIterator();
        while (iterator.hasNext()) {
            BestellPosition detail = (BestellPosition) iterator.next();
            bestellungDetailInsert.update(bestellung.getId(), detail
                    .getAnzahl(), detail.getWare().getId());
            detail.setId(pkQuery.run());    
        }
        return bestellung;
    }

    public List getByIDKunde(int id_kunde) {
        List result = bestellungSelect.execute(id_kunde);
        Iterator iter = result.iterator();
        while (iter.hasNext()) {
            Bestellung bestellung = (Bestellung) iter.next();
            Iterator iterator = bestellungDetailSelect.execute(
                    bestellung.getId()).iterator();
            while (iterator.hasNext()) {
                bestellung.addDetail((BestellPosition) iterator.next());
            }
        }
        return result;
    }

}