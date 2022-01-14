package modulo.prodep.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modulo.prodep.core.model.Usuario;

public class UsuarioMapper implements RowMapper<Usuario>{
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId_usuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setA_paterno(rs.getString("a_paterno"));
        usuario.setA_materno(rs.getString("a_materno"));
        usuario.setPermiso(rs.getInt("permiso"));
        return usuario;
    }
}
