package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class AdminNavigationFormController {
    public Pane navigationPane;
    public AnchorPane root;
    public void initialize() throws IOException {
        //Navigation.onTheTopNavigation(navigationPane, "adminOverViewForm.fxml");

//        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), root);
//        fadeIn.setFromValue(0.0);
//        fadeIn.setToValue(1.0);
//        fadeIn.play();


        Navigation.onTheTopNavigation(navigationPane, "adminOverViewForm.fxml");

    }


    public void btnBranchMangementOnAction(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "branchManagementForm.fxml");
    }

    public void btnBookMangementOnAction(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "bookManagementForm.fxml");
    }

    public void btnAdminMangementOnAction(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "adminManagementForm.fxml");
    }

    public void btnHome(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "adminOverViewForm.fxml");

    }

    public void btnUserOnAction(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "adminUserManagementFrom.fxml");
    }

    public void btnLogoutOnAction(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/loginFrom.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
