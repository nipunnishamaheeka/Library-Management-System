package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.TransactionDto;

public interface TransactionBO extends SuperBO {
    void saveTransaction(TransactionDto dto) throws Exception;

    boolean updateTransaction(String id,TransactionDto dto) throws Exception;

    boolean deleteTransaction(String id) throws Exception;

    TransactionDto searchTransaction(String id) throws Exception;

    ObservableList<TransactionDto> getAllTransaction() throws Exception;

    ObservableList<TransactionDto> getUserTransaction(String user, String status) throws Exception;

    boolean updateStatus(int id,String status) throws Exception ;
}
