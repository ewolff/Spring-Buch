package spring2jdbcdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAO extends SimpleJdbcDaoSupport implements IKundeDAO {

    private static final class KundeResultSetRowMapper implements
            RowMapper<Kunde> {
        public Kunde mapRow(ResultSet rs, int rowNum) throws SQLException {
            Kunde kunde = new Kunde(rs.getString(1), rs.getString(2), rs
                    .getDouble(4));
            kunde.setId(rs.getInt(3));
            return kunde;
        }
    }

    public KundeDAO() {
        super();
    }

    public KundeDAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void deleteByID(int id) {
            getSimpleJdbcTemplate().update("DELETE FROM KUNDE WHERE ID=?", id);
    }

    public Kunde getByID(int id) {
        List<Kunde> result = getSimpleJdbcTemplate()
                .query(
                        "SELECT K.VORNAME, K.NAME, K.ID, K.KONTOSTAND FROM KUNDE K WHERE K.ID=?",
                        new KundeResultSetRowMapper(), id);
        if (result.size() == 0) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<Kunde> getByName(String name) {
        return getSimpleJdbcTemplate()
                .query(
                        "SELECT K.VORNAME, K.NAME, K.ID, K.KONTOSTAND FROM KUNDE K WHERE K.NAME=?",
                        new KundeResultSetRowMapper(), name);
    }

    public void deleteByName(String name) {
        getSimpleJdbcTemplate().update("DELETE FROM KUNDE WHERE NAME=?", name);
    }
    
    public Kunde save(Kunde kunde) {
        getSimpleJdbcTemplate().update(
                "INSERT INTO KUNDE(VORNAME,NAME,KONTOSTAND) VALUES(?,?,?)",
                kunde.getVorname(), kunde.getName(), kunde.getKontostand());
        kunde.setId(getSimpleJdbcTemplate().queryForInt(
                "call identity()"));
        return kunde;
    }


    public void update(Kunde kunde) {
        getSimpleJdbcTemplate().update(
                "UPDATE KUNDE set VORNAME=?, NAME=?, KONTOSTAND=? WHERE ID=?",
                kunde.getVorname(), kunde.getName(), kunde.getKontostand(),
                kunde.getId());

    }

    public List<Kunde> getAll() {
        return getSimpleJdbcTemplate().query(
                "SELECT VORNAME, NAME, ID, KONTOSTAND FROM KUNDE",
                new KundeResultSetRowMapper());
    }
}