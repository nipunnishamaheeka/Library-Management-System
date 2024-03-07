package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.util.Navigation;

import java.io.IOException;

public class UserNavigationFormController {
    public Pane navigationPane;
    public AnchorPane root;


    public void initialize() throws IOException {
        Navigation.onTheTopNavigation(navigationPane, "userDashBoardFrom.fxml");
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    }

