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
public class BranchTm {
    private int id;
    private String name;
    private String location;
    private String email;
    private Button deleteButton;
}
