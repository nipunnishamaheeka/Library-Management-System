package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "book")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

   // private String name;
    private String ImageSrc;
    //private String author;
    private String title;
    private String author;
    private String genre;
    private String status;

//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "book")
//    private List<Transactions> transactions=new ArrayList<>();

}
