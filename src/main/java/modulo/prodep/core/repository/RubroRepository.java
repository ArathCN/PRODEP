package modulo.prodep.core.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import modulo.prodep.core.mapper.RubroMapper;
import modulo.prodep.core.model.Rubro;

@Repository
public class RubroRepository implements RubroRep{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Rubro object) {
        // TODO Auto-generated method stub
        return -1;
    }

    @Override
    public boolean update(Rubro object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Rubro> readAll(Pageable pageable) {
        String sql = "SELECT * FROM rubro;";
        return jdbcTemplate.query(sql, new RubroMapper());
    }

    @Override
    public Rubro readById(int id) {
        String sql = "SELECT * FROM rubro WHERE id_rubro = ?;";
        Object[] params = new Object[] {id};
        List<Rubro> _rubro = jdbcTemplate.query(sql, params, new int[] {java.sql.Types.INTEGER}, new RubroMapper());
        if(_rubro.size() == 1) return _rubro.get(0);
        return null;
    }
}
