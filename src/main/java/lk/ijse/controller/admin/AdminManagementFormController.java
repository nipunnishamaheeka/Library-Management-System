package lk.ijse.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.dto.CredentialsDto;
import lk.ijse.tm.UserManagementTm;

import java.io.IOException;

public class AdminManagementFormController {

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<UserManagementTm> tblAdmin;

    private final CredentialsBO credentialsBO = (CredentialsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREDENTIALS);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllBooks() {
        try {
            boolean isAdmin = true;
            tblAdmin.setItems(credentialsBO.getAll(isAdmin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSignOutOnAction(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnAddAdminOnAction(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/signup.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
