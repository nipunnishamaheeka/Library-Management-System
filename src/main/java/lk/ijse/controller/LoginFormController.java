package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.dao.custom.CredentialsDAO;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.entity.Credentials;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LogInPane;
    public JFXButton btnSignUp;


    @FXML
    private PasswordField txtPasswordHide;

    @FXML
    private TextField txtUserName;
    public static int userID;
    @FXML
    private Label lblWarning;
    private final CredentialsBO credentialsBO = (CredentialsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREDENTIALS);
    @FXML
  void btnSignUpOnAction(ActionEvent actionEvent)  {

        Navigation.switchNavigation("/view/signup.fxml", actionEvent);

    }

public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
    try {
        String username = txtUserName.getText();
        String password = txtPasswordHide.getText();

        if (!validateCredentials(username, password)) {
            return; // Exit if credentials are invalid
        }
        CredentialsDto credentialsDto = credentialsBO.searchUser(username);
        System.out.println(credentialsDto);

        if (credentialsDto != null) {
            if (credentialsDto.getEmail() != null && password.equals(credentialsDto.getPassword())) {
                userID = credentialsDto.getUid();
                if (credentialsDto.isAdmin()) {
                    Navigation.switchNavigation("/view/admin/adminNavigationFrom.fxml", actionEvent);
                } else {
                    Navigation.switchNavigation("/view/user/userNavigationForm.fxml", actionEvent);
                }
            } else {
                lblWarning.setText("Invalid Username or Password!");
            }
        } else {
            lblWarning.setText("Invalid Username or Password!");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public boolean validateCredentials(String username, String password) {
        String usernameRegex = "^[a-zA-Z0-9_]+$";

        String passwordRegex = "^[a-zA-Z0-9@#$%^&+=]+$";

        if (!username.matches(usernameRegex)) {
            lblWarning.setText("Invalid Username format!");
            return false; // Username format is invalid
        }

        if (!password.matches(passwordRegex)) {
            lblWarning.setText("Invalid Password format!");
            return false; // Password format is invalid
        }

        return true; // Username and password are valid
    }



}
