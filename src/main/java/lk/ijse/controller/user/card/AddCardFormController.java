package lk.ijse.controller.user.card;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.controller.user.UserGetBookFormController;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.TransactionDto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AddCardFormController {
    @FXML
    private JFXButton btnReturn;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblDate;
    private TransactionDto dto;
    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);
    @FXML
    void btnReturnOnAction(ActionEvent event) {
        System.out.println(dto);
        UserGetBookFormController.userGetBookFormController.borrowedBooks.remove(dto);
        UserGetBookFormController.userGetBookFormController.loadAllReturns();


    }

    public void setData(TransactionDto dto) {
        this.dto = dto;
        lblBookName.setText(dto.getBookTitle());
        System.out.println(dto.getReturning());
        lblDate.setText(convertDate(dto.getReturning()));


    }

    public String convertDate(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        Date date = new Date(timestamp.getTime());
        return sdf.format(date);
    }
}
