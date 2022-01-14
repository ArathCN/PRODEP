package modulo.prodep.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modulo.prodep.core.model.Fc;

public class FcMapper implements RowMapper<Fc>{

    @Override
    public Fc mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fc fc = new Fc();
        fc.setId_fc(rs.getInt("id_fc"));
        fc.setId_rubro(rs.getInt("id_rubro"));
        fc.setNombre(rs.getString("nombre"));
        fc.setDescripcion(rs.getString("descripcion"));
        return fc;
    }
    
}
