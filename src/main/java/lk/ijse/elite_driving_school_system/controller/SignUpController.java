package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;
    public CheckBox adminCheckBox;
    public CheckBox receptioninstCheckBox;
    public RadioButton adminRadioBtn;
    public RadioButton receptionistRadioBtn;
    public AnchorPane ancMainContainer;
    public StackPane dialogPane;
    public AnchorPane ancLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        
    }

    public void receptioninstCheckBoxOnAction(ActionEvent actionEvent) {
    }

    public void adminCheckBoxOnAction(ActionEvent actionEvent) {
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) {
    }

    public void goToSignInOnAction(MouseEvent mouseEvent) {
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

    public void adminRadioBtnOnAction(ActionEvent actionEvent) {
    }

    public void receptionistRadioBtnOnAction(ActionEvent actionEvent) {
    }
}
