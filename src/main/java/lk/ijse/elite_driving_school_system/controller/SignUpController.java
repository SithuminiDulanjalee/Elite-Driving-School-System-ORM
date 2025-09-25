package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lk.ijse.elite_driving_school_system.bo.BOFactory;
import lk.ijse.elite_driving_school_system.bo.custom.LoginBO;
import lk.ijse.elite_driving_school_system.bo.custom.SignUpBO;
import lk.ijse.elite_driving_school_system.bo.custom.UserBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    SignUpBO signUpBO = (SignUpBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGN_UP);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws ClassNotFoundException, UserAlreadyExistsException, SQLException {
        if (isValied()){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userBO.getNextId());
            userDTO.setUserName(txtUsername.getText().trim());
            userDTO.setPassword(hashPassword(txtPassword.getText().trim()));
            userDTO.setPassword(hashPassword(txtConfirmPassword.getText().trim()));

            if (adminRadioBtn.isSelected()) {
                userDTO.setRole("Admin");
            } else {
                userDTO.setRole("Receptionist");
            }

            try {
                signUpBO.signUp(userDTO);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "ERROR"+e).show();
            }

            txtUsername.clear();
            txtPassword.clear();
            txtConfirmPassword.clear();
            adminRadioBtn.setSelected(false);
            receptionistRadioBtn.setSelected(false);
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter All Fields !!").show();
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
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
        adminRadioBtn.setSelected(true);
        receptionistRadioBtn.setSelected(false);
    }

    public void receptionistRadioBtnOnAction(ActionEvent actionEvent) {
        receptionistRadioBtn.setSelected(true);
        adminRadioBtn.setSelected(false);
    }

    public boolean isValied() {
        return !txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtConfirmPassword.getText().isEmpty() && (adminRadioBtn.isSelected() || receptionistRadioBtn.isSelected());
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void confirmPasswordOnAction(ActionEvent actionEvent) {
        txtConfirmPassword.requestFocus();
    }
}
