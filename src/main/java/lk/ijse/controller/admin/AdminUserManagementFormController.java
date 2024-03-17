package lk.ijse.controller.admin;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CredentialsBO;
import lk.ijse.tm.UserManagementTm;

import java.io.IOException;

public class AdminUserManagementFormController {

    @FXML
    private TableColumn<UserManagementTm, Void> colDelete;

    @FXML
    private TableColumn<UserManagementTm, String> colEmail;

    @FXML
    private TableColumn<UserManagementTm, String> colId;

    @FXML
    private TableColumn<UserManagementTm, String> colName;

    @FXML
    private TableColumn<UserManagementTm, Void> colUpdate;

    @FXML
    private TableView<UserManagementTm> tblUser;

    private final CredentialsBO credentialsBO = (CredentialsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREDENTIALS);

    public void initialize() {
        loadAllBooks();
        setCellValueFactory();
        setupDeleteButton();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllBooks() {
        try {
            boolean isAdmin = false;
            tblUser.setItems(credentialsBO.getAll(isAdmin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDeleteButton() {
        colDelete.setCellFactory(new Callback<>() {
            @Override
            public TableCell<UserManagementTm, Void> call(TableColumn<UserManagementTm, Void> param) {
                return new TableCell<>() {
                    private final Button deleteButton = new Button();
                    {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        deleteButton.setGraphic(deleteIcon);
                        deleteButton.setOnAction(event -> {
                            UserManagementTm userData = getTableView().getItems().get(getIndex());
                            deleteItem(userData);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            }
        });
    }

    private void deleteItem(UserManagementTm userData) {
        try {
            // Perform deletion operation here using credentialsBO
            // For example: credentialsBO.delete(userData.getUid());
            // Then reload the table to reflect changes
            loadAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) throws IOException {
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
