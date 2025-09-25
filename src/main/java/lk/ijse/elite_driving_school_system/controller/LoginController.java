package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lk.ijse.elite_driving_school_system.bo.BOFactory;
import lk.ijse.elite_driving_school_system.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_system.bo.custom.LoginBO;
import lk.ijse.elite_driving_school_system.bo.exception.InvalidCredentialsException;
import lk.ijse.elite_driving_school_system.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField txtUsername;
    public AnchorPane ancMainContainer;
    public AnchorPane ancLogin;
    public TextField txtPassword;
    public Label lblInvalid;
    public StackPane dialogPane;
    private UserDTO userDTO;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);
        if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            try {
                UserDTO loginUser = loginBO.getUser(txtUsername.getText().trim());

                if (checkPassword(txtPassword.getText().trim(), loginUser.getPassword())) {
                    userDTO = loginUser;
                    openMainForm(loginUser.getRole());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid User Password !!").show();
                }
            } catch (InvalidCredentialsException e) {
                new Alert(Alert.AlertType.ERROR, "ERROR").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please Enter All Fields !!").show();
        }

    }

    private void openMainForm(String role) {
        try {
            String fxmlFile;

            if ("Admin".equalsIgnoreCase(role)) {
                fxmlFile = "/view/Dashboard.fxml";
            } else if ("Receptionist".equalsIgnoreCase(role)) {
                fxmlFile = "/view/Dashboard.fxml";
            } else {
                new Alert(Alert.AlertType.ERROR, "Unknown role: " + role).show();
                return;
            }

            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource(fxmlFile)));
            Stage stage = (Stage) ancMainContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

    private void navigateTo(String path) {
        try {
            ancLogin.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            ancLogin.prefWidthProperty().bind(ancMainContainer.widthProperty());
            ancLogin.prefHeightProperty().bind(ancMainContainer.heightProperty());

            anchorPane.prefWidthProperty().bind(ancMainContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainContainer.heightProperty());

            ancLogin.getChildren().add(anchorPane);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Page not found...!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goToSignUpOnAction(MouseEvent mouseEvent) {
        try {
            ancMainContainer.getChildren().clear();
            ancMainContainer.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/SignUp.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
