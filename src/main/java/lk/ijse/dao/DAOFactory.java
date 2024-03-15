package lk.ijse.dao;

import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dao.custom.impl.CredentialsDAOImpl;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.TransactionDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : (daoFactory);
    }
    public enum DAOTypes {
     CREDENTIALS,BRANCH,BOOK,TRANSACTION
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
//            case USER:
////                return new UserDAOImpl();
            case CREDENTIALS:
                return new CredentialsDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case TRANSACTION:
              return new TransactionDAOImpl();
            default:
                return null;
        }
    }
}
