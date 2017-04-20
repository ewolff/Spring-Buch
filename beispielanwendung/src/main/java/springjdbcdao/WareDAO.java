package springjdbcdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import businessobjects.Ware;
import dao.IWareDAO;

public class WareDAO extends NamedParameterJdbcDaoSupport implements IWareDAO {

    private static final class WareRowMapper implements RowMapper<Ware> {

        public Ware mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ware ware = new Ware(rs.getString(1), rs.getDouble(2));
            ware.setId(rs.getInt(3));
            return ware;
        }
    }

    public WareDAO() {
    }

    public WareDAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public List getByBezeichnung(String bezeichnung) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bezeichnung", bezeichnung);
        return getNamedParameterJdbcTemplate()
                .query(
                        "SELECT BEZEICHNUNG, PREIS, ID FROM WARE W WHERE BEZEICHNUNG=:bezeichnung",
                        params, new WareRowMapper());
    }

    public Ware getByID(int id) {
        try {
            return (Ware) getNamedParameterJdbcTemplate().queryForObject(
                    "SELECT BEZEICHNUNG, PREIS, ID FROM WARE W WHERE ID=:id",
                    new MapSqlParameterSource("id", id), new WareRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Ware save(Ware ware) {
        initTemplateConfig();

        getNamedParameterJdbcTemplate()
                .update(
                        "INSERT INTO WARE(BEZEICHNUNG,PREIS) VALUES(:bezeichnung,:preis)",
                        new BeanPropertySqlParameterSource(ware));
        ware.setId(getJdbcTemplate().queryForInt("call identity()"));
        return ware;
    }

    public void deleteByBezeichnung(String bezeichnung) {
        getNamedParameterJdbcTemplate().update(
                "DELETE FROM WARE WHERE BEZEICHNUNG=:bezeichnung",
                new MapSqlParameterSource("bezeichnung", bezeichnung));

    }

    public void deleteByID(int id) {
        getNamedParameterJdbcTemplate().update("DELETE FROM WARE WHERE ID=:id",
                new MapSqlParameterSource("id", id));
    }

    public void update(Ware ware) {
        getNamedParameterJdbcTemplate()
                .update(
                        "UPDATE WARE SET BEZEICHNUNG=:bezeichnung,PREIS=:preis WHERE ID=:id",
                        new BeanPropertySqlParameterSource(ware));
    }

    public List getAll() {
        return (List) getJdbcTemplate().query(
                "SELECT BEZEICHNUNG, PREIS, ID FROM WARE W ",
                new WareRowMapper());
    }

}
