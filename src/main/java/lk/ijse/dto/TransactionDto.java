package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    private int id;
    private String bookTitle;
    private String userName;
    private Timestamp borrowing;
    private Timestamp returning;
    private String status;
}
