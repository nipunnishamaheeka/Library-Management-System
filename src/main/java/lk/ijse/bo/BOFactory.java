package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.bo.custom.impl.CredentialsBOImpl;
import lk.ijse.bo.custom.impl.TransactionBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){
        return (boFactory == null)? boFactory =new BOFactory(): boFactory;

    }
    public enum BOTypes{
    CREDENTIALS,BRANCH,BOOK,TRANSACTION
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CREDENTIALS:
                return new CredentialsBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BOOK:
                return new BookBOImpl();
            case TRANSACTION:
                return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
