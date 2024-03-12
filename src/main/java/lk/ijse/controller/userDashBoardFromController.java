package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.util.Navigation;

public class userDashBoardFromController {
    public Pane navigationPane;

    public void btnEditOnAction(ActionEvent actionEvent) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "userProfileUpdaterForm.fxml");
    }

    public void btnReturnOnAction(MouseEvent event) {
        navigationPane.getChildren().clear();
        Navigation.onTheTopNavigation(navigationPane, "userReturnBookForm.fxml");
    }
}
