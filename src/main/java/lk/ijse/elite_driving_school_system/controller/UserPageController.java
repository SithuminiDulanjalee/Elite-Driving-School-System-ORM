package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public TextField txtPassword;
    public TextField txtRole;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView tblUsers;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPassword;
    public TableColumn colRole;
    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void tblUserOnClickAction(MouseEvent mouseEvent) {
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void txtDurationKeyReleased(KeyEvent keyEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }
}
