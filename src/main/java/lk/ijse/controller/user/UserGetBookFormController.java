package lk.ijse.controller.user;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dto.BookDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class UserGetBookFormController {
    @FXML
    private JFXButton btnSearch;
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colGenure;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<BookDto> tblGetBook;
    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    public void initialize(){
        reload();
        borrowBook();
    }

    private void borrowBook() {
        try {
            tblGetBook.setOnMouseClicked(event -> {
                BookDto selectedItem = tblGetBook.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure want to borrow this Book?", ok, no).showAndWait();
                    if (result.orElse(no) == ok) {
                        getBook(selectedItem.getTitle(), selectedItem.getBranch());
                        updateStatus(selectedItem.getId());
                        reload();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getBook(String title, String branch) {
//        try {
//            LocalDate currentDate = LocalDate.now();
//            LocalDate returnDate = currentDate.plusDays(30);
//
//            TransactionDTO transactionDTO = new TransactionDTO();
//            transactionDTO.setUserName(UserLoginFormController.member);
//            transactionDTO.setBookName(title);
//            transactionDTO.setBranch(branch);
//            transactionDTO.setStatus("Borrow");
//            transactionDTO.setBorrowing(Date.valueOf(currentDate));
//            transactionDTO.setReturning(Date.valueOf(returnDate));
//
//            transactionBO.saveTransaction(transactionDTO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String title = txtSearch.getText();
        if (title != null) {
            try {
                tblGetBook.setItems(bookBO.SearchBookName(title));
                setCellValueFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter Book Name").show();
        }
    }

    private void setCellValueFactory() {
        colBookID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenure.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
    }
    private void loadAllBooks(){
        try {
            tblGetBook.setItems(bookBO.loadAllBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus(int id){
        try {
            String status = "Not Available";
            bookBO.updateStatus(id,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reload(){
        loadAllBooks();
        setCellValueFactory();
    }
}
