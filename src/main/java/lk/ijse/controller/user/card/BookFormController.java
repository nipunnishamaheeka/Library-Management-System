package lk.ijse.controller.user.card;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.controller.user.UserGetBookFormController;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BookFormController {

    private Label lblCount;
    private VBox BookListVBOX;
    @FXML
    private Label authorName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;
    @FXML
    private JFXButton btnCart;

    private TransactionDto transactionDto;
    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    public void setData(Book book,Label lblCount,VBox BookListVBOX) {
        System.out.println(book.getImageSrc());
        Image image = new Image("/" + book.getImageSrc());
        bookImage.setImage(image);
        bookName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
        this.lblCount = lblCount;
        this.BookListVBOX = BookListVBOX;
    }
    private void returnBook(){
        try {
            transactionBO.saveTransaction(transactionDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnCartOnAction(ActionEvent actionEvent) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/view/user/addCardForm.fxml"));
        Parent root = load .load();
        AddCardFormController addCardFormController = load.getController();
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setBookTitle(bookName.getText());
        transactionDto.setId(0);
        transactionDto.setStatus("Borrowed");
        transactionDto.setBorrowing(null);
        transactionDto.setReturning(timestamp);

        this.transactionDto=transactionDto;

        addCardFormController.setData(transactionDto);


       BookListVBOX.getChildren().add(root);

        UserGetBookFormController.userGetBookFormController.borrowedBooks.add(transactionDto);



  int count = Integer.parseInt(lblCount.getText());
        count++;
        lblCount.setText(String.valueOf(count));
        returnBook();
    }
}
