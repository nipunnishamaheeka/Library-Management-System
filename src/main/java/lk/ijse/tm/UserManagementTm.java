package lk.ijse.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserManagementTm {
    private int Uid;

    private String name;

    private String email;

    private Button deleteButton;
}
