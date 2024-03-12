package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.io.IOException;


public class Launcher extends Application {
    public static void main(String []args){
        launch(args);
    }

        public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/userNavigationForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Manage Form");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();


            Session session = SessionFactoryConfig.getInstance().getSession();

            stage.show();
    }
}
