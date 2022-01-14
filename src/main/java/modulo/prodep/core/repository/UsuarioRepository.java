package modulo.prodep.core.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import modulo.prodep.core.model.Usuario;

@Repository
public class UsuarioRepository implements UsuarioRep{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Usuario object) {
        // TODO Auto-generated method stub
        return -1;
    }

    @Override
    public boolean update(Usuario object) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Usuario> readAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario readById(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
