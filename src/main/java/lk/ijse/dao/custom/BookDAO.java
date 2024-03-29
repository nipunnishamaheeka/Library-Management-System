package lk.ijse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Book;

public interface BookDAO extends CrudDAO<Book> {

    ObservableList<Book> searchBooks(String category) throws Exception;
    boolean updateStatus(int id,String status) throws Exception;

    Book searchByUsername(String bookTitle);
}
