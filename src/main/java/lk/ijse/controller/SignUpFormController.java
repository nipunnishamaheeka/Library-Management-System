package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SignUpFormController {

    @FXML
    public JFXButton btnLogin;
    public AnchorPane SignUpPane;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnterPw;

    @FXML
    private TextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        try {

            SignUpPane.getChildren().clear();
            SignUpPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml")));

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), SignUpPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void passwordTxtOnAction(ActionEvent event) {

    }

    @FXML
    void reEnterTxtOnAction(ActionEvent event) {

    }

    @FXML
    void userNameTxtOnAction(ActionEvent event) {

    }
}
