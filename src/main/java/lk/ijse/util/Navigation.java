package lk.ijse.util;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Navigation {


    public static void onTheTopNavigation(Pane pane, String link) {

        try {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/view/user/" + link));
            Parent root = loader.load();
            pane.getChildren().add(root);
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), pane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
