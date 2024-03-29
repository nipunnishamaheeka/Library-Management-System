package lk.ijse.dao.custom.impl;

import jakarta.persistence.criteria.CriteriaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.entity.Credentials;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CredentialsDAOImpl implements CredentialsDAO {
    private Session session;
    @Override
    public boolean save(Credentials addAdmin) throws Exception {
//        Session saveSession = SessionFactoryConfig.getInstance().getSession();
//        Transaction saveTransaction = saveSession.beginTransaction();
//        saveSession.persist(addAdmin);
//        saveTransaction.commit();
//        saveSession.close();
//        return true;
        int value = (int) session.save(addAdmin);
        return value > 0 ?true : false;
    }

    @Override
    public boolean update(int id, Credentials dto) throws Exception {

        session.update(dto);
        return true;

    }

    @Override
    public boolean delete(int id) throws Exception {

        Credentials credentials = session.get(Credentials.class, id);
        session.delete(credentials);
        return false;
    }

    @Override
    public Credentials search(int id) throws Exception {
        return session.get(Credentials.class, id);
    }

    @Override
    public List<Credentials> loadAll() throws Exception {
        return null;

    }

    @Override
    public Credentials searchByUsername(String username) {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Query<Credentials> query = session.createQuery("FROM Credentials WHERE name = : name", Credentials.class);
//        query .setParameter("name", username);
//        Credentials user = query.uniqueResult();
//        session.close();
//
//        return user;

        Query query = session.createQuery("FROM Credentials WHERE name = :name");
        query.setParameter("name", username);
        return (Credentials) query.uniqueResult();

    }

    @Override
    public List<Credentials> loadAll(boolean isAdmin) {
        ObservableList<Credentials> credentials = FXCollections.observableArrayList();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Credentials> query = session.createQuery("FROM Credentials WHERE isAdmin = :isAdmin", Credentials.class);
            query.setParameter("isAdmin", isAdmin);
            credentials.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
