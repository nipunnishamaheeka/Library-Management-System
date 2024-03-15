package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.bo.custom.impl.CredentialsBOImpl;
import lk.ijse.dto.CredentialsDto;

import java.io.IOException;

public class SignUpFormController {

    @FXML
    public JFXButton btnLogin;
    public AnchorPane SignUpPane;



    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton btnSignUP;

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPasswordHide;
    CredentialsBO credentialsBO = new CredentialsBOImpl();

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
    void btnSignUpOnAction(ActionEvent event) throws Exception {
        if (!(txtUserName.getText().isEmpty() && txtPasswordHide.getText().isEmpty() && txtEmail.getText().isEmpty())){
            CredentialsDto credentialsDto = new CredentialsDto(0, txtUserName.getText(), txtEmail.getText(), txtPasswordHide.getText(), null, false);
            Boolean isSaved= credentialsBO.save(credentialsDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR).show();
            }
        }
    }

    @FXML
    void txtPasswordHideOnAction(ActionEvent event) {

    }
    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void reEnterTxtOnAction(ActionEvent event) {

    }

    @FXML
    void userNameTxtOnAction(ActionEvent event) {

    }
}
