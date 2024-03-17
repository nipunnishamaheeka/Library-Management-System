package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.entity.Credentials;
import lk.ijse.tm.UserManagementTm;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialsBOImpl implements CredentialsBO {
    private final CredentialsDAO credentialsDAO = (CredentialsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREDENTIALS);

    @Override
    public boolean save(CredentialsDto dto) throws Exception {

        Session session = SessionFactoryConfig.getInstance().getSession();
        credentialsDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        try{
            Boolean save = credentialsDAO.save(dto.toEntity());
            transaction.commit();
            session.close();
            if (save!=null)return true;
            else return false;
        }catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(CredentialsDto credentialsDto) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        credentialsDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        try {

            Credentials search = credentialsDAO.search(credentialsDto.getUid());
            search.setName(credentialsDto.getName());
            search.setEmail(credentialsDto.getEmail());
            search.setPassword(credentialsDto.getPassword());
            credentialsDAO.update(credentialsDto.getUid(),search);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public CredentialsDto searchUser(String username) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        credentialsDAO.setSession(session);
        Credentials user = credentialsDAO.searchByUsername(username);
        if (user != null) {
            session.close();
            return user.toDTO();
        } else {
            session.close();
            return null;
        }
    }

    @Override
    public void deleteUser(int userId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        credentialsDAO.setSession(session);
        Transaction transaction = session.beginTransaction();
        try {
            credentialsDAO.delete(userId);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();


//        Session session = SessionFactoryConfig.getInstance().getSession();
//        credentialsDAO.setSession(session);
//        Transaction transaction = session.beginTransaction();
//        Credentials user = (Credentials) credentialsDAO.loadAll(userId);
//        credentialsDAO.delete(user);
//        transaction.commit();
//        session.close();
        }
    }


    @Override
    public ObservableList<UserManagementTm> getAll(boolean isAdmin) throws Exception {
        List<Credentials> credentialsList = credentialsDAO.loadAll(isAdmin);
        List<UserManagementTm> credentialsDtos = new ArrayList<>();
        for (Credentials credentials : credentialsList){
            credentialsDtos.add(new UserManagementTm(
                    credentials.getUid(),
                    credentials.getName(),
                    credentials.getEmail(),
                    new Button("Delete")
            ));
        }
        return FXCollections.observableArrayList(credentialsDtos);
    }


}
