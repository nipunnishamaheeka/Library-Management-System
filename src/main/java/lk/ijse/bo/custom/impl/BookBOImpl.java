package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    private final BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean saveBook(BookDto dto) throws Exception {
        return bookDAO.save(new Book(
                dto.getBranch(),
                dto.getAuthor(),
                dto.getTitle(),
                dto.getGenre(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean updateBook(String id, BookDto dto) throws Exception {
        return bookDAO.update(id, new Book(
                dto.getBranch(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getGenre(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean deleteBook(String id) throws Exception {
        return bookDAO.delete(id);
    }

    @Override
    public BookDto searchBook(String id) throws Exception {
        Book book = bookDAO.search(id);
        if (book != null){
            return new BookDto(
                    book.getBranch(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()
            );
        }
        return null;
    }

    @Override
    public ObservableList<BookDto> getAllBooks(String branch) throws Exception {
        List<Book> bookList = bookDAO.getAllBooks(branch);
        List<BookDto> bookDTOS = new ArrayList<>();
        for (Book book : bookList){
            bookDTOS.add(new BookDto(
                    book.getId(),
                    book.getBranch(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus(),
                    book.getDate()
            ));
        }
        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public ObservableList<BookDto> loadAllBooks() throws Exception {
        List<Book> bookList = bookDAO.loadAll();
        List<BookDto> bookDTOS = new ArrayList<>();
        for (Book book : bookList){
            bookDTOS.add(new BookDto(
                    book.getId(),
                    book.getBranch(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus(),
                    book.getDate()
            ));
        }
        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public ObservableList<BookDto> SearchBookName(String name) throws Exception {
        List<Book> bookList = bookDAO.searchBooks(name);
        List<BookDto> bookDTOS = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOS.add(new BookDto(
                    book.getId(),
                    book.getBranch(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus(),
                    book.getDate()
            ));
        }
        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        return bookDAO.updateStatus(id,status);
    }
}
