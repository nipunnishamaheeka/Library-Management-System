package lk.ijse.controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.controller.user.BookFormController;
import lk.ijse.controller.user.CardFormController;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookStoreFormController implements Initializable {
    @FXML
    private HBox cardLayout;
    @FXML
    private GridPane bookContainer;
private List<Book> recentlyAdded;
    private List<Book> recommendedBooks;
@Override
public void initialize(URL location, ResourceBundle resources) {
    recentlyAdded = new ArrayList<>(recentlyAdded());

    recommendedBooks = new ArrayList<>(books());

    int column = 0;
    int row = 1;

//    for (int i = 0; i < recentlyAdded.size(); i++) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/view/cardForm.fxml"));
//
//        try {
//            HBox cardBox = fxmlLoader.load();
//
//
//            CardFormController cardFormController = fxmlLoader.getController();
//            cardFormController.setData(recentlyAdded.get(i));
//            cardLayout.getChildren().add(cardBox);
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    try {
        for (int i = 0; i < recentlyAdded.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/cardForm.fxml"));

            HBox cardBox = fxmlLoader.load();


            CardFormController cardFormController = fxmlLoader.getController();
            cardFormController.setData(recentlyAdded.get(i));
            cardLayout.getChildren().add(cardBox);
        }

        for (Book book : recommendedBooks){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/bookForm.fxml"));

            VBox bookBox = fxmlLoader.load();


            BookFormController bookFormController = fxmlLoader.getController();
            bookFormController.setData(book);
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
    private List<Book> recentlyAdded(){
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V4.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V7.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);
        return ls;

    }

    private List<Book> books(){
        List<Book> ls = new ArrayList<>();
        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V4.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V7.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V4.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);


        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V7.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);

        book = new Book();
        book.setTitle("The Great Gatsby");
        book.setImageSrc("/assets/V6.jpg");
        book.setAuthor("F. Scott Fitzgerald");
        ls.add(book);





        return ls;
    }
}
