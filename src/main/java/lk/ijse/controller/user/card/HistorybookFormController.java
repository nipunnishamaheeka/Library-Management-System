package lk.ijse.controller.user.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDto;

public class HistorybookFormController {
    @FXML
    private Label authorName;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookName;

    public void setData(BookDto bookDto) {
//        bookImage.setImage(new Image(bookDto.getImageSrc()));
        bookName.setText(bookDto.getGenre());
        authorName.setText(bookDto.getAuthor());
    }
}
