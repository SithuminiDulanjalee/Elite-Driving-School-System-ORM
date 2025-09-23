package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LessonPageController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public ComboBox cmbStudent;
    public ComboBox cmbInstructor;
    public ComboBox cmbCourse;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView tblLesson;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colStudent;
    public TableColumn colInstructor;
    public TableColumn colCourse;
    public TableColumn colDate;
    public TableColumn colTime;
    public TextField txtSearch;
    public DatePicker dateDatePicker;
    public TextField txtTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }

    public void tblLessonOnClickAction(MouseEvent mouseEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
