package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Transactions;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private Session session;

    @Override
    public boolean save(Transactions addTransaction) throws Exception {
        Session saveSession = SessionFactoryConfig.getInstance().getSession();
        Transaction saveBranch = saveSession.beginTransaction();
        saveSession.persist(addTransaction);
        saveBranch.commit();
        saveSession.close();
        return true;
    }

    @Override
    public boolean update(int id, Transactions dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public Transactions search(int id) throws Exception {
        return null;
    }

    @Override
    public List<Book> loadAll() throws Exception {
        return null;
    }

    @Override
    public ObservableList<Transactions> getUserTransaction(String user, String status) {
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Transactions> query = session.createQuery("FROM Transactions WHERE user = :user AND status = :status", Transactions.class);
            query.setParameter("userName", user);
            query.setParameter("status",status);
            transactions.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        Session updateSession = SessionFactoryConfig.getInstance().getSession();
        Transaction updateTransaction = updateSession.beginTransaction();
        Transactions existingTransaction = updateSession.get(Transactions.class, id);
        if (existingTransaction!= null) {
            existingTransaction.setStatus(status);
            existingTransaction.setReturning(Timestamp.valueOf (LocalDate.now().atStartOfDay()));
            updateSession.merge(existingTransaction);
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
    public void setSession(Session session) {
this.session = session;
    }
}
