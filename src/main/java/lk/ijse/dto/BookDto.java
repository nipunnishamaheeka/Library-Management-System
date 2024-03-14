package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookDto {
private int id;
private String branch;
private String title;
private String author;
private String genre;
private String status;
private Timestamp date;

public BookDto(String branch, String title, String author, String genre, String status) {
        this.branch = branch;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
}
}
