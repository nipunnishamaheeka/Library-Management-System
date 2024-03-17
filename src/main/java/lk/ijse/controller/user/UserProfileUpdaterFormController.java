package lk.ijse.controller.user;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.bo.custom.impl.CredentialsBOImpl;
import lk.ijse.controller.LoginFormController;
import lk.ijse.dto.BranchDto;
import lk.ijse.dto.CredentialsDto;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserProfileUpdaterFormController {
    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnUploadPhoto;

    @FXML
    private Pane photoPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    private int Uid;

    @FXML
    private TextField txtConformPassword;
    private CredentialsBO credentialsBO =new CredentialsBOImpl();
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            credentialsBO.deleteUser(LoginFormController.userID);
        }
       // Navigation.switchNavigation("login-form.fxml",event);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
//        boolean profileValid = validateProfile();
//        if (profileValid) {
//            String name = txtName.getText();
//            String email = txtEmail.getText();
//            String password = txtPassword.getText();
//
//
//            try {
//                boolean isUpdated = credentialsBO.update(new CredentialsDto(name,email,password));
//                if (isUpdated) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
//                    clearFields();
//                    loadAllItems();
//                }
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
        boolean isPasswordValid = validatePassword();
        if (isPasswordValid) {
            try {
                String fullname = txtName.getText();
                String email = txtEmail.getText();
                String password = txtPassword.getText();

                if (password.equals(txtConformPassword.getText())) {

                    try {
                        boolean isSaved = credentialsBO.update(new CredentialsDto(Uid,fullname,email, password,null,false));
                        if (isSaved) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
                            return;
                        }
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Password Not Matched").show();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private boolean validateTextField(TextField textField, String patternRegex) {
        String text = textField.getText();
        boolean isValid = Pattern.compile(patternRegex).matcher(text).matches();

        if (!isValid) {
            textField.setStyle("-fx-border-color: red");
            //new Shake(textField).play();
            return false;
        } else {
            textField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    private boolean validatePassword() {
        return validateTextField(txtName, "^[A-Za-z\\s]{3,50}$")
                && validateTextField(txtEmail, "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}")
                && validateTextField(txtPassword, "\\d{2,}");


    }
    @FXML
    void btnUploadPhotoOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg","*.gif"));

        File selectedFile = fileChooser.showOpenDialog(btnUploadPhoto.getScene().getWindow());

        if (selectedFile !=null) {

        }
    }

}
