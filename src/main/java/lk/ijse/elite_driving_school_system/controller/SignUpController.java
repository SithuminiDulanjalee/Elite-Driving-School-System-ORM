package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtConfirmPassword;
    public CheckBox adminCheckBox;
    public CheckBox receptioninstCheckBox;

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
}
