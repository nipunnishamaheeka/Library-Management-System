package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LogInPane;
    public JFXButton btnSignUp;


    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        try {
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup.fxml"));
            Parent root = loader.load();


            SignUpFormController signUpFormController = loader.getController();

            Scene loginScene = new Scene(root);

            Stage currentStage = (Stage) LogInPane.getScene().getWindow();

            TranslateTransition transition = new TranslateTransition(Duration.millis(350), root);
            transition.setFromX(-currentStage.getWidth());
            transition.setToX(0);


            currentStage.setScene(loginScene);
            currentStage.show();
            transition.play();

            Stage signUpStage = (Stage) LogInPane.getScene().getWindow();
            signUpStage.close();*/

            LogInPane.getChildren().clear();
            LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/signup.fxml")));

            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), LogInPane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        LogInPane.getChildren().clear();
        //LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/userNavigationForm.fxml")));
        LogInPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/admin/adminNavigationFrom.fxml")));

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), LogInPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

}
