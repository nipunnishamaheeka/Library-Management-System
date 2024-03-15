package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.entity.Credentials;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CredentialsBOImpl implements CredentialsBO {
    private final CredentialsDAO credentialsDAO = (CredentialsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREDENTIALS);

    @Override
    public boolean save(CredentialsDto dto) throws Exception {
//        return credentialsDAO.save(new Credentials(
//                dto.getUid(),
//                dto.getName(),
//                dto.getEmail(),
//                dto.getPassword(),
//                dto.getImageSrc(),
//                dto.isAdmin()
//
//
//
//        ));
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
    public ObservableList<CredentialsDto> getAll() throws Exception {
        return null;
    }
//    @Override
//    public Credentials searchUserId(String name)  {
//        Credentials credential = credentialsDAO.searchUserId(name);
//        if (credential != null){
//            return new Credentials(
//                    credential.getAid(),
//                    credential.getUid()
//
//            );
//        }
//        return null;
//    }
}
