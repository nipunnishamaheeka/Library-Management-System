package lk.ijse.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.dto.BookDto;
import lk.ijse.tm.BookTm;
import lk.ijse.tm.BranchTm;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminBookManagementFormController {
    @FXML
    private JFXComboBox cmbGenre;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAuthor;

    @FXML
    private JFXButton btnUploadPhoto;
    private String selectedImageSrc;
    public static AdminBookManagementFormController booksManagementFormController;

    BookBO bookBO = new BookBOImpl();
    public void initialize(){

        loadCmbBox();
        setCellValueFactory();
//        loadAllItems();
    }


    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));


    }

    private void loadCmbBox() {

        ObservableList<String> genres= FXCollections.observableArrayList();
        genres.addAll(
                "Science Fiction",
                "Satire",
                "Drama",
                "Action and Adventure",
                "Romance",
                "Mystery",
                "Horror",
                "Self help",
                "Health",
                "Guide",
                "Travel",
                "Children's",
                "Religion, Spirituality & New Age",
                "Science",
                "History",
                "Math",
                "Anthology",
                "Poetry",
                "Encyclopedias",
                "Dictionaries",
                "Comics",
                "Art",
                "Cookbooks",
                "Diaries",
                "Journals",
                "Prayer books",
                "Series",
                "Trilogy",
                "Biographies",
                "Autobiographies",
                "Fantasy"
        );


        cmbGenre.setItems(genres);

    }
    @FXML
    void btnChoosePhotoOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(btnUploadPhoto.getScene().getWindow());

        if (selectedFile != null) {
            // Set the selected image source path to the field
            selectedImageSrc = selectedFile.getAbsolutePath();
        }
    }

    @FXML
    void txtSaveOnAction(ActionEvent event) {
        boolean isBookValid = validateBook();
        if (isBookValid && selectedImageSrc != null) { // Check if an image is selected
            String name = txtName.getText();
            String author = txtAuthor.getText();
            String status = "Available";
            String genre = cmbGenre.getSelectionModel().getSelectedItem().toString();

            BookDto bookDto = new BookDto(0, name, author, genre, status, selectedImageSrc);

            try {
                boolean isSaved = bookBO.save(bookDto);

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
            new Alert(Alert.AlertType.ERROR, "Please select an image!", ButtonType.OK).show();
        }
    }
//    @FXML
//    void btnChoosePhotoOnAction(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select File");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.jpeg","*.gif"));
//
//        File selectedFile = fileChooser.showOpenDialog(btnUploadPhoto.getScene().getWindow());
//
//        if (selectedFile !=null) {
//
//
//        }
//
//    }
//
//    @FXML
//    void txtSaveOnAction(ActionEvent event) {
//        boolean isBookValid = validateBook();
//        if (isBookValid) {
//
//            String name = txtName.getText();
//            String author = txtAuthor.getText();
//            String imageSrc =  btnChoosePhotoOnAction();
//            String status = "Available";
//            String genre = cmbGenre.getSelectionModel().getSelectedItem().toString();
//
////            String timeStamp = "2021-08-12";
//
//            BookDto bookDto = new BookDto(0, name, author, genre, status, imageSrc);
//
//            try {
//                boolean isSaved = bookBO.save(bookDto);
//
//                if (isSaved) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!", ButtonType.OK).show();
//                    clearFields();
//                    loadAllItems();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Not Saved!", ButtonType.OK).show();
//                }
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            // new Alert(Alert.AlertType.ERROR, "Invalid Details", ButtonType.OK).show();
//        }
//    }

    private void loadAllItems() {
        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        try {
            List<BookDto> dtoList = bookBO.getAll();
            if (dtoList != null) {
                List<BookTm> tmlist = dtoList.stream().map(bookDto -> new BookTm(
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getGenre(),
                        bookDto.getStatus(),
                        bookDto.getImageSrc(),
                        new Button("Delete")
                )).collect(Collectors.toList());
                for (BookTm dto : tmlist) {
                    Button deleteButton = new Button();
                    Button updateButton = new Button();

                    deleteButton.setCursor(Cursor.HAND);
                    updateButton.setCursor(Cursor.HAND);

                    deleteButton.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this book?", yes, no).showAndWait();
                        if (result.orElse(no) == yes) {
                            int selectedIndex = tblBook.getSelectionModel().getSelectedIndex();
                            System.out.println("badu hari");
                            int code = (int) colID.getCellData(selectedIndex);
                            System.out.println(code);
                            try {
                                // tblBook.delete(code);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            obList.remove(selectedIndex);
                            tblBook.refresh();
                        }
                    });
                    obList.add(
                            new BookTm(
                                    dto.getId(),
                                    dto.getTitle(),
                                    dto.getAuthor(),
                                    dto.getGenre(),
                                    dto.getStatus(),
                                    dto.getImageSrc(),
                                    deleteButton
                            )
                    );
                }
                tblBook.setItems(obList);
                setFontAwesomeIcons();

                // Add a click event listener to the table rows
                tblBook.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1) { // Check for a single click
                        int selectedIndex = tblBook.getSelectionModel().getSelectedIndex();
                        if (selectedIndex != -1) {
                            // Assuming that you have a method to set the data to text fields
                            setDataToFields(selectedIndex);
                        }
                    }
                });
            }
            //setDoctorID();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataToFields(int selectedIndex) {
        BookTm dto = tblBook.getItems().get(selectedIndex);
        setFields(dto);
    }

    private void setFields(BookTm dto) {
        txtName.setText(dto.getTitle());
        txtAuthor.setText(dto.getAuthor());
        cmbGenre.getSelectionModel().select(dto.getGenre());

    }

    private void setFontAwesomeIcons() {
        tblBook.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            deleteButton.setGraphic(deleteIcon);

        });
    }

    private void clearFields() {
        txtName.clear();
        txtAuthor.clear();
        cmbGenre.getSelectionModel().clearSelection();
    }

    private boolean validateBook() {
        String nameText = txtName.getText();
        boolean isNameValid = nameText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isNameValid) {
            txtName.setStyle("-fx-border-color: red");
            //new animatefx.animation.Shake(txtName).play();
            return false;
        }
        String authorText = txtAuthor.getText();
        boolean isAuthorValid = authorText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isAuthorValid) {
            txtName.setStyle("-fx-border-color: red");
            //new animatefx.animation.Shake(txtName).play();
            return false;
        }
        return true;
    }
}
