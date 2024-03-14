package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.entity.Transactions;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;

public class TransactionDAOImpl implements TransactionDAO {

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
    public boolean update(String id, Transactions dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Transactions search(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<Transactions> loadAll() throws Exception {
        return null;
    }

    @Override
    public ObservableList<Transactions> getUserTransaction(String user, String status) {
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Transactions> query = session.createQuery("FROM Transactions WHERE userName = :userName AND status = :status", Transactions.class);
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
            existingTransaction.setReturning(Date.valueOf(LocalDate.now()));
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
}
