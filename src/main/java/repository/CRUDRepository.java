package repository;

import java.util.List;

public interface CRUDRepository<T,ID> extends  SuperDao{

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);

    List<T> getAll();

    T search(ID id);
}
