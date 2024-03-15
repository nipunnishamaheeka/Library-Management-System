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
//        try {
//            LogInPane.getChildren().clear();
//            LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signup.fxml")));
//
//            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), LogInPane);
//            fadeIn.setFromValue(0.0);
//            fadeIn.setToValue(1.0);
//            fadeIn.play();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Navigation.switchNavigation("view/signup.fxml", actionEvent);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        try {
            CredentialsDto credentialsDto = credentialsBO.searchUser(txtUserName.getText());
            System.out.println(credentialsDto);

            if (credentialsDto != null) {
                if (credentialsDto.getEmail() != null && txtPasswordHide.getText().equals(credentialsDto.getPassword())) {
                    userID=credentialsDto.getUid();
                    if (credentialsDto.isAdmin()) {
                        Navigation.switchNavigation("/view/admin/adminNavigationFrom.fxml", actionEvent);
                    } else {
                        Navigation.switchNavigation("/view/user/userNavigationForm.fxml", actionEvent);
                    }
                }
            } else {
                lblWarning.setText("Invalid Username or Password !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private void searchUser(){
//        String username = txtUserName.getText();
//        String password = txtPasswordHide.getText();
//        try {
//            CredentialsDto credentials = credentialsBO.searchUser(username);
//            if (credentials == null || !credentials.getPassword().equals(password)){
//                new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
//            } else {
//                member=username;
//                LogInPane.getChildren().clear();
//                //LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/userNavigationForm.fxml")));
//                LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/admin/adminNavigationFrom.fxml")));
//
//                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), LogInPane);
//                fadeIn.setFromValue(0.0);
//                fadeIn.setToValue(1.0);
//                fadeIn.play();
//            }
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.WARNING,"Incorrect username or password").show();
//        }
//    }

}
