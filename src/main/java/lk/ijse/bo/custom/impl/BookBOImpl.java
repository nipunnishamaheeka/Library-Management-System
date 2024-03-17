package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    private Session session;
    private final BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean save(BookDto dto) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setImageSrc(dto.getImageSrc());

        bookDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        boolean save = bookDAO.save(book);
        transaction.commit();

        session.close();
        return save;

    }

    @Override
    public boolean updateBook(int id, BookDto dto) throws Exception {
        session = SessionFactoryConfig.getInstance().getSession();
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setImageSrc(dto.getImageSrc());

        bookDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        boolean update = bookDAO.update(id,book);
        transaction.commit();
        session.close();
        return update;
    }

    @Override
    public void delete(int id) throws Exception {


        session = SessionFactoryConfig.getInstance().getSession();
        bookDAO.setSession(session);
        bookDAO.delete(id);

        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();

    }

    @Override
    public BookDto searchBook(int id) throws Exception {

        Session session = SessionFactoryConfig.getInstance().getSession();
        bookDAO.setSession(session);

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
        session.close();
        return null;
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        try {


            Session session = SessionFactoryConfig.getInstance().getSession();
            bookDAO.setSession(session);
            List<Book> bookList = bookDAO.loadAll();
            System.out.println(bookList);

            session.close();
            return bookList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BookDto> getAll() throws Exception {
        return null;
    }

    @Override
    public ObservableList<BookDto> loadAllBooks() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        bookDAO.setSession(session);

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
        session.close();
        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public ObservableList<BookDto> SearchBookName(String name) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        bookDAO.setSession(session);


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
        session.close();

        return FXCollections.observableArrayList(bookDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();

        bookDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        boolean updateStatus = bookDAO.updateStatus(id,status);
        transaction.commit();

        session.close();

        return updateStatus;
    }
}
