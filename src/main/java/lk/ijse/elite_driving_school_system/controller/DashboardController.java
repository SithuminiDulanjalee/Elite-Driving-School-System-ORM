package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public AnchorPane ancMainContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/HomePage.fxml");
    }
    private void navigateTo(String path){
        try {
            ancMainContainer.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            AnchorPane.setTopAnchor(anchorPane, 0.0);
            AnchorPane.setBottomAnchor(anchorPane, 0.0);
            AnchorPane.setLeftAnchor(anchorPane, 0.0);
            AnchorPane.setRightAnchor(anchorPane, 0.0);

            ancMainContainer.getChildren().add(anchorPane);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Page not found...!").show();
        }
    }

    public void btnHomePageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/HomePage.fxml");
    }

    public void btnStudentPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/StudentPage.fxml");
    }

    public void btnCoursePageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/CoursePage.fxml");
    }

    public void btnInstructorPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/InstructorPage.fxml");
    }

    public void btnPaymentPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PaymentPage.fxml");
    }

    public void btnLessonPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/LessonPage.fxml");
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnReportPageOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutPageOnAction(ActionEvent actionEvent) {
        Parent load = null;
        try {
            load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(load);
        Stage stage = (Stage) this.ancMainContainer.getScene().getWindow();
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setTitle("Elite Driving School");
        stage.show();
    }

    public void btnUserPageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/UserPage.fxml");
    }
}
