package modulo.prodep.core.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import modulo.prodep.core.mapper.FcMapper;
import modulo.prodep.core.model.Fc;
import modulo.prodep.core.model.Rubro;

@Repository
public class FcRepository implements FcRep{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RubroRepository rubroRepository;

    @Autowired
    @Qualifier("getDSBean")
    private DataSource dataSource;

    @Override
    public int create(Fc fc) {
        try {
            SimpleJdbcInsert simpleJdbcInsert = 
                new SimpleJdbcInsert(dataSource).withTableName("fc").usingGeneratedKeyColumns("id_fc");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("id_fc", fc.getId_fc());
            parameters.put("id_rubro", fc.getId_rubro());
            parameters.put("nombre", fc.getNombre());
            parameters.put("descripcion", fc.getDescripcion());
            Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
            return id.intValue();
        } catch (Exception e) {
            return -1;
        }   
    }

    @Override
    public boolean update(Fc fc) {
        if(fc.getId_fc() > 0){
            int affectedRows = 0;
            /* String sql2 = String.format(
                "UPDATE fc SET id_rubro = '%d', nombre = '%s', descripcion='%s' WHERE id_fc = %d",
                fc.getId_rubro(), fc.getNombre(), fc.getDescripcion(), fc.getId_fc()); */
            String sql = "UPDATE fc SET id_rubro = ?, nombre = ?, descripcion= ? WHERE id_fc = ?";
            try {
                affectedRows = jdbcTemplate.update(sql, fc.getId_rubro(), fc.getNombre(), fc.getDescripcion(), fc.getId_fc());
            } catch (Exception e) {
                return false;
            }
            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Fc> readAll(Pageable pageable) {
        String sql = "SELECT * FROM fc;";
        return jdbcTemplate.query(sql, new FcMapper());
    }

    @Override
    public List<Fc> readByRubro (int id_rubro){
        String sql = "SELECT * FROM fc WHERE id_rubro = ? ;";
        Object[] params = new Object[] {id_rubro};
        //int[] types = new int[] {java.sql.Types.INTEGER};
        List<Fc> fcs = jdbcTemplate.query(sql, new FcMapper(), params);
        Rubro rubro = rubroRepository.readById(id_rubro);
        for (Fc fc : fcs) {
            fc.setRubro(rubro);
        }
        return fcs;
    }

    @Override
    public Fc readById(int id) {
        String sql = "SELECT * FROM fc WHERE id_fc = ?;";
        Object[] params = new Object[] {id};
        List<Fc> _fc = jdbcTemplate.query(sql, params, new int[] {java.sql.Types.INTEGER}, new FcMapper());
        if(_fc.size() == 1) return _fc.get(0);
        return null;
    }


}
