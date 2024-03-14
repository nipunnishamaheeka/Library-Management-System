package lk.ijse.dao.custom.impl;


import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book addBook) {
        Session saveSession = SessionFactoryConfig.getInstance().getSession();
        Transaction saveBranch = saveSession.beginTransaction();
        saveSession.persist(addBook);
        saveBranch.commit();
        saveSession.close();
        return true;
    }

    @Override
    public boolean delete(String id){
        Session deleteSession = SessionFactoryConfig.getInstance().getSession();
        Transaction deleteTransaction = deleteSession.beginTransaction();
        Book deleteBook = deleteSession.get(Book.class, id);
        if (deleteBook != null){
            deleteSession.remove(deleteBook);
            deleteTransaction.commit();
            deleteSession.close();
            return true;
        } else {
            return false;
        }
    }
//check this
    @Override
    public boolean update(String id, Book dto) {
        Session updateSession = SessionFactoryConfig.getInstance().getSession();
        Transaction updateTransaction = updateSession.beginTransaction();
        Book existingBook = updateSession.get(Book.class, id);
        if (existingBook!= null) {
            existingBook.setBranch(dto.getBranch());
            existingBook.setTitle(dto.getTitle());
           // existingBook.setAuthor(dto.getAuthor());
            existingBook.setGenre(dto.getGenre());
            existingBook.setStatus(dto.getStatus());
            updateSession.merge(existingBook);
        } else {
            updateTransaction.commit();
            updateSession.close();
            return false;
        }
        updateTransaction.commit();
        updateSession.close();
        return true;
    }

    @Override
    public Book search(String id) {
        Session searchSession = SessionFactoryConfig.getInstance().getSession();
        Transaction searchTransaction = searchSession.beginTransaction();
        Book getBooks = searchSession.get(Book.class,id);
        searchTransaction.commit();
        searchSession.close();
        return getBooks;
    }

    @Override
    public ObservableList<Book> loadAll() {
        ObservableList<Book> allBookList = FXCollections.observableArrayList();
        Session loadSession = SessionFactoryConfig.getInstance().getSession();
        CriteriaQuery<Book> criteriaQuery = loadSession.getCriteriaBuilder().createQuery(Book.class);
        criteriaQuery.from(Book.class);
        List<Book> customersList = loadSession.createQuery(criteriaQuery).getResultList();
        allBookList.setAll(customersList);
        loadSession.close();
        return allBookList;
    }

    @Override
    public ObservableList<Book> getAllBooks(String branch) {
        ObservableList<Book> allBookList = FXCollections.observableArrayList();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE branch = :branch", Book.class);
            query.setParameter("branch", branch);
            allBookList.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBookList;
    }

    @Override
    public ObservableList<Book> searchBooks(String title) throws Exception {
        ObservableList<Book> allBookList = FXCollections.observableArrayList();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE title = :title", Book.class);
            query.setParameter("title", title);
            allBookList.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBookList;
    }

    @Override
    public boolean updateStatus(int id,String status) {
        Session updateSession = SessionFactoryConfig.getInstance().getSession();
        Transaction updateTransaction = updateSession.beginTransaction();

        Book existingBook = updateSession.get(Book.class, id);
        if (existingBook!= null) {
            existingBook.setStatus(status);
            updateSession.merge(existingBook);
        } else {
            updateTransaction.commit();
            updateSession.close();
            return false;
        }
        updateTransaction.commit();
        updateSession.close();
        return true;
    }
}
