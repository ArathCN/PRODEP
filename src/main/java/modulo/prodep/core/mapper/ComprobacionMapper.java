package modulo.prodep.core.mapper;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.jdbc.core.RowMapper;
import modulo.prodep.core.model.Comprobacion;

public class ComprobacionMapper implements RowMapper<Comprobacion>{
    @Override
    public Comprobacion mapRow(ResultSet rs, int rowNum) throws SQLException{
        Date fecha, ultima_revision;
        Comprobacion comprobacion = new Comprobacion();
        comprobacion.setId_comp(rs.getInt("id_comp"));
        comprobacion.setId_usuario(rs.getInt("id_usuario"));
        comprobacion.setId_fc(rs.getInt("id_fc"));
        comprobacion.setDoc_name(rs.getString("doc_name"));
        comprobacion.setDoc_type(rs.getString("doc_type"));
        comprobacion.setDoc(rs.getBlob("doc").getBytes(1, (int)rs.getBlob("doc").length()));
        comprobacion.setEstado(rs.getInt("estado"));
        comprobacion.setComentario(rs.getString("comentario"));

        try{
            fecha = Comprobacion.df.parse(rs.getString("fecha") + Comprobacion.zona_horaria);
            ultima_revision = Comprobacion.df.parse(rs.getString("ultima_revision") + Comprobacion.zona_horaria);
            comprobacion.setFecha(fecha);
            comprobacion.setUltima_revision(ultima_revision);
        }catch (ParseException except) {
            except.printStackTrace();
        }

        return comprobacion;
    }
}
