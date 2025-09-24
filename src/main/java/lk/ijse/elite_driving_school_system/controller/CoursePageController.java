package lk.ijse.elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.elite_driving_school_system.bo.BOFactory;
import lk.ijse.elite_driving_school_system.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_system.entity.Course;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView<CourseTM> tblCourses;
    public TableColumn<CourseTM, String> colId;
    public TableColumn<CourseTM, String> colName;
    public TableColumn<CourseTM, String> colDescription;
    public TableColumn<CourseTM, String> colDuration;
    public TableColumn<CourseTM, String> colFee;
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtDuration;
    public TextField txtFee;
    public Button btnReset;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void tblCourseOnClickAction(MouseEvent mouseEvent) {

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {

    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();
            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            txtId.clear();
            txtName.clear();
            txtDescription.clear();
            txtDuration.clear();
            txtFee.clear();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong..").show();
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }
    public void txtDurationKeyReleased(KeyEvent keyEvent) {}

    public void txtFeeKeyReleased(KeyEvent keyEvent) {
    }

}