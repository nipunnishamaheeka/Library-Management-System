package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {
    private int id;
    private String branch;
    private String bookTitle;
    private String userName;
    private Date borrowing;
    private Date returning;
    private String status;
}
