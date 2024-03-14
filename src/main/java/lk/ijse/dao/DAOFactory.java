package lk.ijse.dao;

import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.dao.custom.impl.BookDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : (daoFactory);
    }
    public enum DAOTypes {
     USER,ADMIN,BRANCH,BOOK,TRANSACTION
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case USER:
//                return new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case BRANCH:
//                return new BranchDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case TRANSACTION:
//                return new TransactionDAOImpl();
            default:
                return null;
        }
    }
}
