package modulo.prodep.core.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import modulo.prodep.core.model.Comprobacion;

public interface CombrobacionRep extends BaseRep<Comprobacion>{
    public List<Comprobacion> readAllCompleteInfo(int estado, Pageable pageable);
    public boolean updateAceptar(Comprobacion comprobacion);
    public boolean updateRechazar(Comprobacion comprobacion);
    public List<Comprobacion> readByUserAndFc (int id_usuario, int id_fc);
    public boolean deleteById (int id);
    public List<Comprobacion> readByUser (int id_usuario);
    
}
