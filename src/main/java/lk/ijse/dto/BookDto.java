package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookDto {
private int id;
private String title;
private String author;
private String genre;
private String status;
//private Timestamp date;
private String ImageSrc;

public BookDto( String title, String author, String genre, String status,String ImageSrc) {

        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.ImageSrc = ImageSrc;
}
}
