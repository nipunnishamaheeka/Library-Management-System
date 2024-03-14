package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "transaction")
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    private String userName;

    @Column(name = "book_title")
    private String bookName;

    private String branch;

    private Date borrowing;

    private Date returning;

    private String status;

    @ManyToMany(mappedBy = "transactionList")
    private List<Book> bookList = new ArrayList<>();

    public Transactions(String userName, String bookName, String branch, Date borrowing, Date returning) {
        this.userName = userName;
        this.bookName = bookName;
        this.branch = branch;
        this.borrowing = borrowing;
        this.returning = returning;
    }
}
