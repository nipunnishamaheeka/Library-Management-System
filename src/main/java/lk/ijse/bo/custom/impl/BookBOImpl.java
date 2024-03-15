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
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setImageSrc(dto.getImageSrc());

        return bookDAO.save(book);
    }

    @Override
    public boolean updateBook(int id, BookDto dto) throws Exception {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setImageSrc(dto.getImageSrc());

        return bookDAO.update(id, book);
    }

    @Override
    public boolean deleteBook(int id) throws Exception {
        return bookDAO.delete(id);
    }

    @Override
    public BookDto searchBook(int id) throws Exception {
        Book book = bookDAO.search(id);
        if (book != null){
            return new BookDto(
                    book.getId(),
                    book.getImageSrc(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()

            );
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        List<Book> bookList = bookDAO.loadAll();
        return bookList;
    }

    @Override
    public ObservableList<BookDto> loadAllBooks() throws Exception {
        List<Book> bookList = bookDAO.loadAll();
        List<BookDto> bookDTOS = new ArrayList<>();
        for (Book book : bookList){
            bookDTOS.add(new BookDto(
                    book.getId(),
                    book.getImageSrc(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()
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
                    book.getImageSrc(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getStatus()
            ));
        }
        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        return bookDAO.updateStatus(id,status);
    }
}
