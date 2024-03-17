package lk.ijse.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.controller.user.card.HistorybookFormController;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHistoryFormController {
    @FXML
    private GridPane bookContainer;
    public Label lblCount;
    public VBox BookListVBOX;
    private List<Book> recommendedBooks;
    private final BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    private BookDto bookDto;

    public void initialize() throws Exception {
        recommendedBooks = books();

        int column = 0;
        int row = 1;

        try {
            for (Book book : recommendedBooks) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/user/history-bookForm.fxml"));
                VBox bookBox = fxmlLoader.load();

                System.out.println(book.getId());

                BookDto bookDto1 = new BookDto();
                bookDto1.setId(book.getId());
                bookDto1.setImageSrc(bookDto1.getImageSrc());
                bookDto1.setAuthor(book.getAuthor());
                bookDto1.setTitle(book.getTitle());
                bookDto1.setGenre(book.getGenre());
                bookDto1.setStatus(book.getStatus());
                HistorybookFormController historyBookFormController = fxmlLoader.getController();
                historyBookFormController.setData(bookDto1);


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
}
