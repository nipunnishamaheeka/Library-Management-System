package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.util.List;

public interface BookBO extends SuperBO {
    boolean save(BookDto dto) throws Exception;

    boolean updateBook(int id,BookDto dto) throws Exception;

    void delete(int id) throws Exception;


    BookDto searchBook(int id) throws Exception;

    List<Book> getAllBooks() throws Exception;
    List<BookDto> getAll() throws Exception;
    ObservableList<BookDto> loadAllBooks() throws Exception;

    ObservableList<BookDto> SearchBookName(String name) throws Exception;

    boolean updateStatus(int id,String status) throws Exception;


}
