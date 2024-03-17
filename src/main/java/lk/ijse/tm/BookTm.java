package lk.ijse.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookTm {


        private int id;
        private String title;
        private String author;
        private String genre;
        private String status;
        //private Timestamp date;
        private String ImageSrc;
        private Button deleteButton;
}
