package modulo.prodep.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modulo.prodep.core.model.Rubro;

public class RubroMapper implements RowMapper<Rubro>{
    @Override
    public Rubro mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rubro rubro = new Rubro();
        rubro.setId_rubro(rs.getInt("id_rubro"));
        rubro.setNombre(rs.getString("nombre"));
        rubro.setDescripcion(rs.getString("descripcion"));
        rubro.setImg_url(rs.getString("img_url"));
        return rubro;
    }
}
