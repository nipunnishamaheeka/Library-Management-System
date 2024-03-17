package lk.ijse.controller.user;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.controller.user.card.AddCardFormController;
import lk.ijse.controller.user.card.BookFormController;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;
import lk.ijse.util.Navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserGetBookFormController {
    @FXML
    private AnchorPane navigationPane;
    public Label lblCount;
    public VBox BookListVBOX;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private GridPane bookContainer;
    private List<Book> recommendedBooks;

    public static UserGetBookFormController userGetBookFormController;
    public List<TransactionDto> borrowedBooks;


    @FXML
    private TextField txtSearch;

    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    private final TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);


    public UserGetBookFormController() {
        borrowedBooks=new ArrayList<>();
        userGetBookFormController = this;
    }

    public void loadAllReturns(){
        BookListVBOX.getChildren().clear();
        lblCount.setText(String.valueOf(borrowedBooks.size()));
        try {
            System.out.println(borrowedBooks);
            for (TransactionDto transactionDto : borrowedBooks) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/user/addCardForm.fxml"));
                Parent root = fxmlLoader.load();
                AddCardFormController addCardFormController = fxmlLoader.getController();
                addCardFormController.setData(transactionDto);
                BookListVBOX.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws Exception {

        borrowBook();

        recommendedBooks = books();

        int column = 0;
        int row = 1;

        try {
            for (Book book : recommendedBooks) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/user/bookForm.fxml"));

                VBox bookBox = fxmlLoader.load();


                BookFormController bookFormController = fxmlLoader.getController();
                bookFormController.setData(book,lblCount,BookListVBOX);
//            cardLayout.getChildren().add(cardBox);

                if (column == 6) {
                    column = 0;
                    ++row;
                }
                bookContainer.add(bookBox, column++, row);
                GridPane.setMargin(bookBox, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Book> books() throws Exception {
        List<Book> ls = new ArrayList<>();
        List<Book> allBooks = bookBO.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book.getImageSrc());
        }
        return allBooks;
    }

    private void borrowBook() {

    }

    private void getBook(String title) {
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
//        String title = txtSearch.getText();
//        if (title != null) {
//            try {
//                tblGetBook.setItems(bookBO.SearchBookName(title));
//                //setCellValueFactory();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            new Alert(Alert.AlertType.WARNING,"Please Enter Book Name").show();
//        }
    }

    private void loadAllBooks() {

    }

    private void updateStatus(int id) {
//        try {
//            String status = "Not Available";
//            bookBO.updateStatus(id, status);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    public void btnEditOnAction(ActionEvent actionEvent) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "/view/user/userProfileUpdaterForm.fxml");
    }

}
