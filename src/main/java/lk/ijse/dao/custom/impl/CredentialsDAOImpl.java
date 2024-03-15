package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Credentials;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CredentialsDAOImpl implements CredentialsDAO {
    private Session session;
    @Override
    public boolean save(Credentials addAdmin) throws Exception {
        Session saveSession = SessionFactoryConfig.getInstance().getSession();
        Transaction saveTransaction = saveSession.beginTransaction();
        saveSession.persist(addAdmin);
        saveTransaction.commit();
        saveSession.close();
        return true;
    }

    @Override
    public boolean update(int id, Credentials dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public Credentials search(int id) throws Exception {
        return null;
    }

    @Override
    public List<Book> loadAll() throws Exception {
        return null;
    }

    @Override
    public Credentials searchByUsername(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Query<Credentials> query = session.createQuery("FROM Credentials WHERE name = : name", Credentials.class);
        query .setParameter("name", username);
        Credentials user = query.uniqueResult();
        session.close();

        return user;

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
