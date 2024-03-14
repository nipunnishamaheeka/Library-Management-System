package lk.ijse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Transactions;

public interface TransactionDAO extends CrudDAO<Transactions> {

    ObservableList<Transactions> getUserTransaction(String user, String status);

    boolean updateStatus(int id,String status);
}
