package lk.ijse.bo;

import lk.ijse.bo.custom.impl.AdminBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){
        return (boFactory == null)? boFactory =new BOFactory(): boFactory;

    }
    public enum BOTypes{
    ADMIN,USER,BRANCH,BOOK,TRANSACTION
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case ADMIN:
                return new AdminBOImpl();
            case USER:
               // return new UserBOImpl();
            case BRANCH:
                //return new BranchBOImpl();
            case BOOK:
               // return new BookBOImpl();
            case TRANSACTION:
               // return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
