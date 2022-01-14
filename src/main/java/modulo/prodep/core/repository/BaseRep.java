package modulo.prodep.core.repository;

import java.util.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface BaseRep<T> {
    public int create (T object);
    public boolean update (T object);
    public List<T> readAll (Pageable pageable);
    public T readById (int id);
}
