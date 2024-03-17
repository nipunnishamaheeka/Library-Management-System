package lk.ijse.controller.admin;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDto;
import lk.ijse.tm.BranchTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BranchManagementFormController {
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtManager;

    @FXML
    private TextField txtName;

    @FXML
    private JFXButton btnSave;
    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEdit;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colManager;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<BranchTm> tblBranch;
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    private int itemId;

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isBranchValid = validateBranch();
        if (isBranchValid) {

            String name = txtName.getText();
            String address = txtLocation.getText();
            String email = txtEmail.getText();
//            String timeStamp = "2021-08-12";

            BranchDto branchDto = new BranchDto(0, name, address, email);

            try {
                boolean isSaved = branchBO.save(branchDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // new Alert(Alert.AlertType.ERROR, "Invalid Details", ButtonType.OK).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean branchValid = validateBranch();
        if (branchValid) {
            String name = txtName.getText();
            String address = txtLocation.getText();
            String email = txtEmail.getText();
            try {
                boolean isUpdated = branchBO.update(new BranchDto(itemId, name, address, email));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                    clearFields();
                    loadAllItems();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    private void loadAllItems() {
        ObservableList<BranchTm> obList = FXCollections.observableArrayList();

        try {
            List<BranchDto> dtoList = branchBO.getAll();
            List<BranchTm> tmlist = dtoList.stream().map(branchDto -> new BranchTm(
                    branchDto.getId(),
                    branchDto.getName(),
                    branchDto.getLocation(),
                    branchDto.getEmail(),
                    new Button("Delete")
            )).collect(Collectors.toList());
            for (BranchTm dto : tmlist) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this branch?", yes, no).showAndWait();
                    if (result.orElse(no) == yes) {
                        int selectedIndex = tblBranch.getSelectionModel().getSelectedIndex();
                        System.out.println("badu hari");
                        int code = (int) colID.getCellData(selectedIndex);
                        System.out.println(code);
                        try {
                            branchBO.delete(code);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        obList.remove(selectedIndex);
                        tblBranch.refresh();
                    }
                });
                obList.add(
                        new BranchTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getLocation(),
                                dto.getEmail(),
                                deleteButton
                        )
                );
            }
            tblBranch.setItems(obList);
            setFontAwesomeIcons();

            // Add a click event listener to the table rows
            tblBranch.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { // Check for a single click
                    int selectedIndex = tblBranch.getSelectionModel().getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Assuming that you have a method to set the data to text fields
                        setDataToFields(selectedIndex);
                    }
                }
            });
            //setDoctorID();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void setFontAwesomeIcons() {

        tblBranch.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            deleteButton.setGraphic(deleteIcon);

        });
    }
    private void setDataToFields(int selectedIndex) {
        BranchTm dto = tblBranch.getItems().get(selectedIndex);
        setFields(dto);
    }

    private void setFields(BranchTm dto) {
        this.itemId=dto.getId();
        txtName.setText(dto.getName());
        txtLocation.setText(dto.getLocation());
        txtEmail.setText(dto.getEmail());
    }

    private void clearFields() {
        txtName.clear();
        txtLocation.clear();
        txtEmail.clear();
    }


    private boolean validateBranch() {
        String nameText = txtName.getText();
        boolean isNameValid = nameText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isNameValid) {
            txtName.setStyle("-fx-border-color: red");
            //new animatefx.animation.Shake(txtName).play();
            return false;
        }
        String emailText = txtEmail.getText();
        boolean isEmailValid = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}").matcher(emailText).matches();
        if (!isEmailValid) {
            txtEmail.setStyle("-fx-border-color: red");
            //new animatefx.animation.Shake(txtEmail).play();
            return false;
        }
        String locationText = txtLocation.getText();
        boolean isLocationValid = locationText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isLocationValid) {
            txtLocation.setStyle("-fx-border-color: red");
            //new animatefx.animation.Shake(txtLocation).play();
            return false;
        }

        return true;

    }
}
