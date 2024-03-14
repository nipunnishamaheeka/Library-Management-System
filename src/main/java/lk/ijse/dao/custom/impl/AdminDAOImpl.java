package lk.ijse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.entity.Admin;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean save(Admin addAdmin) throws Exception {
        Session saveSession = SessionFactoryConfig.getInstance().getSession();
        Transaction saveTransaction = saveSession.beginTransaction();
        saveSession.persist(addAdmin);
        saveTransaction.commit();
        saveSession.close();
        return true;
    }

    @Override
    public boolean update(String id, Admin dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Admin search(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<Admin> loadAll() throws Exception {
        return null;
    }

    @Override
    public Admin searchAdmin(String name, String password) throws Exception {
        Session search = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = search.beginTransaction();
        Query<Admin> query = search.createQuery("FROM Admin WHERE name = : name AND password = : password", Admin.class);
               query .setParameter("name", name);
                query.setParameter("password", password);

        Admin admin = query.uniqueResult();
        transaction.commit();
        search.close();

        return admin;
    }
}
