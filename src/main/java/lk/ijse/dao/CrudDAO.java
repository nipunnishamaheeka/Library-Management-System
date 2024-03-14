package lk.ijse.dao;

import javafx.collections.ObservableList;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto) throws Exception;

    boolean update(String id,T dto) throws Exception;

    boolean delete(String id) throws Exception;
    T search(String id) throws Exception;

    ObservableList<T> loadAll() throws Exception;
}
