package modulo.prodep.core.repository;

import java.util.List;
import modulo.prodep.core.model.Fc;

public interface FcRep extends BaseRep<Fc>{
    public List<Fc> readByRubro (int id_rubro);
}
