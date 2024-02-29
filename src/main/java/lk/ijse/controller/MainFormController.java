package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;



public class MainFormController {


    public Label lblDescription;
    public AnchorPane root;
    public Pane btnAddBook;
    public Label lblMenu;
    public ImageView imgAddBook;
    public Pane btnIssueBook;
    public Pane btnAddStudent;
    public Pane btnReturnBook;
    public Pane btnViewStatistics;

    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getTarget() instanceof Pane) {
            Pane pane = (Pane) event.getTarget();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1);
            scaleT.setToY(1);

            scaleT.play();

            pane.setEffect(null);
//            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getTarget() instanceof Pane) {
            Pane pane = (Pane) event.getTarget();

            switch (pane.getId()) {
                case "btnAddBook":
                    lblMenu.setText("Add Books");
                    lblDescription.setText("Click to add ");
                    break;
                case "btnIssueBook":
                    lblMenu.setText("Issue Book");
                    lblDescription.setText("Click to Issue Book, edit, delete, search or view Book");
                    break;
                case "btnAddStudent":
                    lblMenu.setText("Add Student");
                    lblDescription.setText("Click here if you want to place a new order");
                    break;
                case "btnReturnBook":
                    lblMenu.setText("Return Book");
                    lblDescription.setText("Click if you want to Return");
                    break;
                case "btnViewStatistics":
                    lblMenu.setText("View Statistics");
                    lblDescription.setText("Click if you want to View Statistics");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), pane);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

//            DropShadow glow = new DropShadow();
//            glow.setColor(Color.CORNFLOWERBLUE);
//            glow.setWidth(20);
//            glow.setHeight(20);
//            glow.setRadius(20);
//            pane.setEffect(glow);
        }
    }

}
