package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "transaction")
public class Transactions  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int id;

    @Column(name = "book_title")
    private String bookName;

    private String branch;

    private Timestamp borrowing;

    private Timestamp returning;

    private String status;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Credentials user;

    public Transactions( String bookName, Timestamp borrowing, Timestamp returning) {

        this.bookName = bookName;
        this.borrowing = borrowing;
        this.returning = returning;
    }
}
