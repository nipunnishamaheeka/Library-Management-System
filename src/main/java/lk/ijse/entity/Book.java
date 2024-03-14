package lk.ijse.entity;

import jakarta.persistence.*;
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
public class Book implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

   // private String name;
    private String ImageSrc;
    //private String author;
    private String branchName;
    private String title;
    private String Author;
    private String genre;
    private String status;
    @CreationTimestamp
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToMany()
    private List<Transactions> transactionList = new ArrayList<>();

    public Book(String branchName, String title, String author, String genre, String status) {
        this.branchName = branchName;
        this.title = title;
       this.Author = author;
        this.genre = genre;
        this.status = status;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getI mageSrc() {
//        return ImageSrc;
//    }
//
//    public void setImageSrc(String imageSrc) {
//        ImageSrc = imageSrc;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }






}
