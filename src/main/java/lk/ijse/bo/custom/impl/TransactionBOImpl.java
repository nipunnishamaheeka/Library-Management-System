package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Credentials;
import lk.ijse.entity.Transactions;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    private final BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    private final CredentialsDAO credentialsDAO = (CredentialsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CREDENTIALS);
    private final TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

    @Override
    public boolean saveTransaction(TransactionDto dto) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Credentials credentials = credentialsDAO.searchByUsername(dto.getUserName());

        Book book = bookDAO.searchByUsername(dto.getBookTitle());
        book.setStatus("Not Available");

        Transactions transactions = new Transactions();

        transactions.setId(dto.getId());
        transactions.setBranch(dto.getBookTitle());
        transactions.setBorrowing(dto.getBorrowing());
        transactions.setReturning(dto.getReturning());
        transactions.setStatus(dto.getStatus());

        credentials.getTransactions().add(transactions);
        System.out.println(book.getTransactions());
        book.getTransactions().add(transactions);

        credentialsDAO.save(credentials);
        bookDAO.save(book);

        transactionDAO.setSession(session);
        transactionDAO.save(transactions);
        transaction.commit();

        session.close();

        return false;
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
        List<Transactions> transactionList = transactionDAO.getUserTransaction(user, status);
        List<TransactionDto> transactionDTOS = new ArrayList<>();
        for (Transactions transaction : transactionList) {
            transactionDTOS.add(new TransactionDto(
                    transaction.getId(),
                    transaction.getBranch(),
                    transaction.getBookName(),
                    transaction.getBorrowing(),
                    transaction.getReturning(),
                    transaction.getStatus()
            ));
        }
        return FXCollections.observableArrayList(transactionDTOS);
    }

    @Override
    public boolean updateStatus(int id, String status) throws Exception {
        return transactionDAO.updateStatus(id, status);
    }
}
