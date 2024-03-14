package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;

public interface BookBO extends SuperBO {
    boolean saveBook(BookDto dto) throws Exception;

    boolean updateBook(String id,BookDto dto) throws Exception;

    boolean deleteBook(String id) throws Exception;

    BookDto searchBook(String id) throws Exception;

    ObservableList<BookDto> getAllBooks(String branch) throws Exception;

    ObservableList<BookDto> loadAllBooks() throws Exception;

    ObservableList<BookDto> SearchBookName(String name) throws Exception;

    boolean updateStatus(int id,String status) throws Exception;


}
