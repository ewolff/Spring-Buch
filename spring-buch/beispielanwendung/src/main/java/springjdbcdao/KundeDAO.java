package springjdbcdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import businessobjects.Kunde;
import dao.IKundeDAO;

public class KundeDAO extends JdbcDaoSupport implements IKundeDAO {

	private static final class KundeResultSetRowMapper implements RowMapper<Kunde> {
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
		getJdbcTemplate().update("DELETE FROM KUNDE WHERE ID=?",
				new Object[] { new Integer(id) });
	}

	public Kunde getByID(int id) {
		try {
			return (Kunde) getJdbcTemplate()
					.queryForObject(
							"SELECT VORNAME, NAME, ID, KONTOSTAND FROM KUNDE K WHERE ID=?",
							new Object[] { new Integer(id) },
							new KundeResultSetRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	public List getByName(String name) {
		return getJdbcTemplate().query(
				"SELECT VORNAME, NAME, ID, KONTOSTAND FROM KUNDE WHERE NAME=?",
				new Object[] { name }, new KundeResultSetRowMapper());
	}

	public void deleteByName(String name) {
		getJdbcTemplate().update("DELETE FROM KUNDE WHERE NAME=?",
				new Object[] { name });
	}

	public Kunde save(Kunde kunde) {
		getJdbcTemplate().update(
				"INSERT INTO KUNDE(VORNAME,NAME,KONTOSTAND) VALUES(?,?,?)",
				new Object[] { kunde.getVorname(), kunde.getName(),
						new Double(kunde.getKontostand()) });
		kunde.setId(getJdbcTemplate().queryForInt("call identity()"));
		return kunde;
	}

	public void update(Kunde kunde) {
		getJdbcTemplate().update(
				"UPDATE KUNDE set VORNAME=?, NAME=?, KONTOSTAND=? WHERE ID=?",
				new Object[] { kunde.getVorname(), kunde.getName(),
						new Double(kunde.getKontostand()),
						new Integer(kunde.getId()) });

	}

	public List getAll() {
		List result = getJdbcTemplate().query(
				"SELECT VORNAME, NAME, ID, KONTOSTAND FROM KUNDE",
				new Object[] {}, new KundeResultSetRowMapper());
		return result;
	}
}