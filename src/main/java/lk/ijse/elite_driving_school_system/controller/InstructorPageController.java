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

public class InstructorPageController implements Initializable {
    public TextField txtInstructorId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtSpecialization;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView tblInstructor;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPhone;
    public TableColumn colEmail;
    public TableColumn colSpecialization;
    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void tblInstructorOnClickAction(MouseEvent mouseEvent) {
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }

    public void txtPhoneKeyReleased(KeyEvent keyEvent) {
    }

    public void txtEmailKeyReleased(KeyEvent keyEvent) {
    }
}
