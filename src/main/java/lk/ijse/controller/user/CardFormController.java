package lk.ijse.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lk.ijse.entity.Book;

public class CardFormController {

    @FXML
    private Label authorName;

    @FXML
    private Label bookName;

    @FXML
    private HBox box;

    @FXML
    private ImageView bookImage;
private String [] colors = {"B9E5FF" , "BDB2FE" , "FFC3A0" , "FFD3B6" };
    public void setData (Book book){
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
        bookImage.setImage(image);

        bookName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
box.setStyle("-fx-background-color:#"+colors [(int)(Math.random()*colors.length)] +";"+
        "-fx-background-radius: 15;" +
        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0) ,10 ,0 , 0,10)");

    }
}
