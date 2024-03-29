package lk.ijse.dao.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    private Session session;
    @Override
    public boolean save(Book addBook) {
//        Session saveSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction saveBranch = saveSession.beginTransaction();
//        saveSession.persist(addBook);
//        saveBranch.commit();
//        saveSession.close();
//        return true;
        int value = (int) session.save(addBook);
        return value > 0 ?true : false;
    }

    @Override
    public boolean delete(int id){
//        Session deleteSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction deleteTransaction = deleteSession.beginTransaction();
//        Book deleteBook = deleteSession.get(Book.class, id);
//        if (deleteBook != null){
//            deleteSession.remove(deleteBook);
//            deleteTransaction.commit();
//            deleteSession.close();
//            return true;
//        } else {
//            return false;
//        }
        Book book = session.get(Book.class, id);
        session.delete(book);
        return false;
    }
//check this
    @Override
    public boolean update(int id, Book dto) {
//        Session updateSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction updateTransaction = updateSession.beginTransaction();
//        Book existingBook = updateSession.get(Book.class, id);
//        if (existingBook!= null) {
//            existingBook.setTitle(dto.getTitle());
//           // existingBook.setAuthor(dto.getAuthor());
//            existingBook.setGenre(dto.getGenre());
//            existingBook.setStatus(dto.getStatus());
//            updateSession.merge(existingBook);
//        } else {
//            updateTransaction.commit();
//            updateSession.close();
//            return false;
//        }
//        updateTransaction.commit();
//        updateSession.close();
//        return true;
        session.update(dto);
        return true;
    }

    @Override
    public Book search(int id) {
//        Session searchSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction searchTransaction = searchSession.beginTransaction();
//        Book getBooks = searchSession.get(Book.class,id);
//        searchTransaction.commit();
//        searchSession.close();
//        return getBooks;
        return session.get(Book.class,id);
    }

    @Override
    public List<Book> loadAll() {
//        Session loadSession = SessionFactoryConfig.getInstance().getSession();
//        CriteriaQuery<Book> criteriaQuery = loadSession.getCriteriaBuilder().createQuery(Book.class);
//        criteriaQuery.from(Book.class);
//        List<Book> books = loadSession.createQuery(criteriaQuery).getResultList();
//
//        loadSession.close();
//        return books;
        return session.createQuery("FROM Book").list();
    }



    @Override
    public ObservableList<Book> searchBooks(String title) throws Exception {
//        ObservableList<Book> allBookList = FXCollections.observableArrayList();
//        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
//            Query<Book> query = session.createQuery("FROM Book WHERE title = :title", Book.class);
//            query.setParameter("title", title);
//            allBookList.addAll(query.getResultList());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return allBookList;

        Query query = session.createQuery("FROM Book WHERE title = :title");
        query.setParameter("title", title);
        return FXCollections.observableArrayList(query.getResultList());
    }

    @Override
    public boolean updateStatus(int id,String status) {
//        Session updateSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction updateTransaction = updateSession.beginTransaction();
//
//        Book existingBook = updateSession.get(Book.class, id);
//        if (existingBook!= null) {
//            existingBook.setStatus(status);
//            updateSession.merge(existingBook);
//        } else {
//            updateTransaction.commit();
//            updateSession.close();
//            return false;
//        }
//        updateTransaction.commit();
//        updateSession.close();
//        return true;
        Book book = session.get(Book.class, id);
        book.setStatus(status);
        session.update(book);
        return true;
    }

    @Override
    public Book searchByUsername(String bookTitle) {
//        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
//            Query<Book> query = session.createQuery("FROM Book WHERE title = :title", Book.class);
//            query.setParameter("title", bookTitle);
//            Book result = query.getSingleResult();
//            session.close();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
        Query<Book> query = session.createQuery("FROM Book WHERE title = :title", Book.class);
        query.setParameter("title", bookTitle);
        Book result = query.getSingleResult();
        return result;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
