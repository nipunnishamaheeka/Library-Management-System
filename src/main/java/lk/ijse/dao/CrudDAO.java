package lk.ijse.dao;

import lk.ijse.entity.Book;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T dto) throws Exception;

    boolean update(int id,T dto) throws Exception;

    boolean delete(int id) throws Exception;
    T search(int id) throws Exception;

    List<Book> loadAll() throws Exception;
}
