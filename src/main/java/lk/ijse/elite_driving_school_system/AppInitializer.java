package lk.ijse.elite_driving_school_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import org.hibernate.SessionFactory;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FactoryConfiguration.getInstance();
        Parent load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Elite Driving School");
        stage.show();
    }
}
