package modulo.prodep.core.repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import modulo.prodep.core.configuration.DocumentState;
import modulo.prodep.core.mapper.ComprobacionCIMapper;
import modulo.prodep.core.mapper.ComprobacionMapper;
import modulo.prodep.core.model.Comprobacion;

@Repository
public class ComprobacionRepository implements CombrobacionRep{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("getDSBean")
    private DataSource dataSource;
    
    @Override
    public int create(Comprobacion comprobacion) {
        try {
            SimpleJdbcInsert simpleJdbcInsert = 
                new SimpleJdbcInsert(dataSource).withTableName("comprobaciones").usingGeneratedKeyColumns("id_comp");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("id_usuario", comprobacion.getId_usuario());
            parameters.put("id_fc", comprobacion.getId_fc());
            parameters.put("doc_name", comprobacion.getDoc_name());
            parameters.put("doc_type", comprobacion.getDoc_type());
            parameters.put("doc", comprobacion.getDoc());
            parameters.put("fecha", Comprobacion.df.format(comprobacion.getFecha()));
            parameters.put("estado", comprobacion.getEstado());
            parameters.put("ultima_revision", Comprobacion.df.format(comprobacion.getUltima_revision()));
            parameters.put("comentario", comprobacion.getComentario());
            Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
            
            return id.intValue();
        } catch (Exception e) {
            return -1;
        }    
    }

    @Override
    public boolean update(Comprobacion comprobacion) { //modificar lod e la fehca
        if(comprobacion.getId_comp() > 0){
            int affectedRows = 0;
            String sql = "UPDATE comprobaciones SET doc_type = ?, doc = ?, estado = ?, ultima_revision= ?, comentario = ? WHERE id_comp = ?";
            try {
                affectedRows = jdbcTemplate.update(
                    sql,
                    comprobacion.getDoc_type(),
                    new javax.sql.rowset.serial.SerialBlob(comprobacion.getDoc()),
                    comprobacion.getEstado(),
                    Comprobacion.df.format(comprobacion.getUltima_revision()),
                    comprobacion.getComentario(), comprobacion.getId_comp()
                );
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
    public boolean updateAceptar(Comprobacion comprobacion){
        if(comprobacion.getId_comp() > 0){
            int affectedRows = 0;
            String sql = "UPDATE comprobaciones SET estado = ?, ultima_revision= ? WHERE id_comp = ?";
            try {
                affectedRows = jdbcTemplate.update(sql, comprobacion.getEstado(), Comprobacion.df.format(comprobacion.getUltima_revision()), comprobacion.getId_comp());
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
    public boolean updateRechazar(Comprobacion comprobacion){
        if(comprobacion.getId_comp() > 0){
            int affectedRows = 0;
            String sql = "UPDATE comprobaciones SET estado = ?, ultima_revision= ?, comentario = ? WHERE id_comp = ?";
            try {
                affectedRows = jdbcTemplate.update(sql, comprobacion.getEstado(), Comprobacion.df.format(comprobacion.getUltima_revision()), comprobacion.getComentario(), comprobacion.getId_comp());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Comprobacion> readAll(Pageable pageable) {
        String sql = "SELECT * FROM comprobaciones;";
        return jdbcTemplate.query(sql, new ComprobacionMapper());
    }

    @Override
    public Comprobacion readById(int id) {
        String sql = "SELECT * FROM comprobaciones WHERE id_comp = ?;";
        Object[] params = new Object[] {id};
        Comprobacion comprobacion;
        try{
            comprobacion = jdbcTemplate.queryForObject(sql, params, new int[] {java.sql.Types.INTEGER}, new ComprobacionMapper());
        }catch(DataAccessException e){
            comprobacion = null;
        }
        return comprobacion;
    }

    @Override
    public List<Comprobacion> readAllCompleteInfo(int estado, Pageable pageable){
        //estado -> 1 (En revisión)
        //estado -> 2 (Listo)
        //estado -> 3 (Rechazado)
        //estado -> 0 (todos)
        //String sqlFcs = "SELECT distinct comprobaciones.id_fc, fc.id_rubro, fc.nombre, fc.descripcion FROM comprobaciones INNER JOIN fc ON comprobaciones.id_fc = fc.id_fc group by comprobaciones.id_fc";
        //String sqlUsuario = "select distinct comprobaciones.id_usuario, usuario.nombre, usuario.a_paterno, usuario.a_materno, usuario.permiso from comprobaciones inner join usuario on comprobaciones.id_usuario = usuario.id_usuario";
        String sql = "SELECT C.id_comp, C.id_usuario, U.nombre, U.a_paterno, U.a_materno, U.permiso, C.id_fc, F.id_rubro, F.nombre AS 'nombre_fc', F.descripcion, C.doc_name, C.doc_type, C.doc, C.fecha, C.estado, C.ultima_revision, C.comentario " +
        "FROM comprobaciones AS C " +
        "INNER JOIN fc AS F ON C.id_fc = F.id_fc " +
        "INNER JOIN usuario AS U on C.id_usuario = U.id_usuario ";
        if(estado != DocumentState.CUALQUIERA) sql += " WHERE estado = " + estado + " ";
        sql += "ORDER BY C.id_usuario ASC;";
        //List<Usuario> usuarios = jdbcTemplate.query(sqlUsuario, new UsuarioMapper());
        return jdbcTemplate.query(sql, new ComprobacionCIMapper());
    }

    @Override
    public List<Comprobacion> readByUserAndFc (int id_usuario, int id_fc){
        String sql = "SELECT * FROM comprobaciones WHERE id_usuario = ? AND id_fc = ?;";
        Object[] params = new Object[] {id_usuario, id_fc};
        int[] types = new int[] {java.sql.Types.INTEGER, java.sql.Types.INTEGER};
        return jdbcTemplate.query(sql, params, types, new ComprobacionMapper());
    }

    @Override
    public List<Comprobacion> readByUser (int id_usuario){
        String sql = "SELECT C.id_comp, C.id_usuario, U.nombre, U.a_paterno, U.a_materno, U.permiso, C.id_fc, F.id_rubro, F.nombre AS 'nombre_fc', F.descripcion, C.doc_name, C.doc_type, C.doc, C.fecha, C.estado, C.ultima_revision, C.comentario " +
        "FROM comprobaciones AS C " +
        "INNER JOIN fc AS F ON C.id_fc = F.id_fc " +
        "INNER JOIN usuario AS U on C.id_usuario = U.id_usuario " +
        "WHERE C.id_usuario = ?";
        //String sql = "SELECT * FROM comprobaciones WHERE id_usuario = ?;";
        Object[] params = new Object[] {id_usuario};
        int[] types = new int[] {java.sql.Types.INTEGER};
        return jdbcTemplate.query(sql, params, types, new ComprobacionCIMapper());
    }

    @Override
    public boolean deleteById (int id){
        if(id > 0){
            int affectedRows = 0;
            String sql = "DELETE FROM comprobaciones WHERE id_comp = ?";
            try {
                affectedRows = jdbcTemplate.update(sql, id);
            } catch (Exception e) {
                return false;
            }
            if(affectedRows > 0){
                return true;
            }
        }
        return false;
    }
}
