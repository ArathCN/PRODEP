package modulo.prodep.core.mapper;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;
import modulo.prodep.core.model.Comprobacion;

public class ComprobacionCIMapper implements RowMapper<Comprobacion>{
    @Override
    public Comprobacion mapRow(ResultSet rs, int rowNum) throws SQLException{
        Date fecha, ultima_revision;
        Comprobacion comprobacion = new Comprobacion();
        comprobacion.setId_comp(rs.getInt(1));
        comprobacion.setId_usuario(rs.getInt(2));
        comprobacion.setId_fc(rs.getInt(7));
        comprobacion.setDoc_url(rs.getString(11));
        comprobacion.setEstado(rs.getInt(13));
        comprobacion.setComentario(rs.getString(15));

        //datos del usuario
        comprobacion.getUsuario().setId_usuario(rs.getInt(2));
        comprobacion.getUsuario().setNombre(rs.getString(3));
        comprobacion.getUsuario().setA_paterno(rs.getString(4));
        comprobacion.getUsuario().setA_materno(rs.getString(5));
        comprobacion.getUsuario().setPermiso(rs.getInt(6));

        //datos del fc
        comprobacion.getFc().setId_fc(rs.getInt(7));
        comprobacion.getFc().setId_rubro(rs.getInt(8));
        comprobacion.getFc().setNombre(rs.getString(9));
        comprobacion.getFc().setDescripcion(rs.getString(10));

        try{
            fecha = Comprobacion.df.parse(rs.getString(12) + Comprobacion.zona_horaria);
            ultima_revision = Comprobacion.df.parse(rs.getString(14) + Comprobacion.zona_horaria);
            comprobacion.setFecha(fecha);
            comprobacion.setUltima_revision(ultima_revision);
        }catch (ParseException except) {
            except.printStackTrace();
        }
        return comprobacion;
    }
}
