package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Transactions;

import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {
    private final TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);
    @Override
    public boolean saveTransaction(TransactionDto dto) throws Exception {
        return transactionDAO.save(new Transactions(
                dto.getUserName(),
                dto.getBookTitle(),
                dto.getBranch(),
                dto.getBorrowing(),
                dto.getReturning()
        ));
    }

    @Override
    public boolean updateTransaction(String id, TransactionDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTransaction(String id) throws Exception {
        return false;
    }

    @Override
    public TransactionDto searchTransaction(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<TransactionDto> getAllTransaction() throws Exception {
        return null;
    }

    @Override
    public ObservableList<TransactionDto> getUserTransaction(String user, String status) throws Exception {
        List<Transactions> transactionList = transactionDAO.getUserTransaction(user,status);
        List<TransactionDto> transactionDTOS = new ArrayList<>();
        for (Transactions transaction : transactionList) {
            transactionDTOS.add(new TransactionDto(
                    transaction.getId(),
                    transaction.getBranch(),
                    transaction.getBookName(),
                    transaction.getUserName(),
                    transaction.getBorrowing(),
                    transaction.getReturning(),
                    transaction.getStatus()
            ));
        }
        return FXCollections.observableArrayList(transactionDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        return transactionDAO.updateStatus(id,status);
    }
}
