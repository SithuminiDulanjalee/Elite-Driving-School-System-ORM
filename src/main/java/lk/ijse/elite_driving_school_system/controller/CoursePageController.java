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
import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_system.dto.tm.InstructorTM;
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
    public TableColumn<CourseTM, Double> colFee;
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtDuration;
    public TextField txtFee;
    public Button btnReset;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    private final String namePattern = "^[A-Za-z ]+$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtId.setText(courseBO.getNextId());
            txtId.setEditable(false);
            setCellValueFactory();
            loadTableData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resetPage();

    }

    private void setCellValueFactory() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadTableData();

        txtId.setText(courseBO.getNextId());
        txtId.setEditable(false);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        boolean isValidName = name.matches(namePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");

        if (!(isValidName)) {
            new Alert(Alert.AlertType.WARNING, "Fail to save course").show();
            return;
        }

        try {
            CourseDTO dto = new CourseDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtDescription.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtFee.getText())
            );
            if (courseBO.saveCourse(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Course saved successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to save course").show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail").show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        boolean isValidName = name.matches(namePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");

        if (!(isValidName)) {
            new Alert(Alert.AlertType.WARNING, "Fail to update course").show();
            return;
        }

        try {
            CourseDTO dto = new CourseDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtDescription.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtFee.getText())
            );
            if (courseBO.updateCourse(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to update course").show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail").show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText().trim();
        if(id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Id can not be empty").show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete course " + id + "?");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (courseBO.deleteCourse(id)) {
                    new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete course").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
                e.printStackTrace();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        resetPage();

    }

    public void tblCourseOnClickAction(MouseEvent mouseEvent) {
        CourseTM selected = tblCourses.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtId.setText(selected.getCourseId());
            txtName.setText(selected.getCourseName());
            txtDescription.setText(selected.getDescription());
            txtDuration.setText(selected.getDuration());
            txtFee.setText(String.valueOf(selected.getFee()));

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<CourseTM> obList = FXCollections.observableArrayList();
            for (CourseDTO dto : courseBO.getAllCourses()) {
                obList.add(new CourseTM(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getDescription(),
                        dto.getDuration(),
                        dto.getFee()
                ));
            }
            tblCourses.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load course: " + e.getMessage()).show();
            e.printStackTrace();
        }

    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        try {
            txtId.setText(courseBO.getNextId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID: " + e.getMessage()).show();
            e.printStackTrace();
        }

    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();
            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
//            txtId.clear();
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

    public void txtSearchKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String searchText = txtSearch.getText().toLowerCase();
        ObservableList<CourseTM> allCourses = FXCollections.observableArrayList();
        ObservableList<CourseTM> filteredList = FXCollections.observableArrayList();

        for (CourseDTO dto : courseBO.getAllCourses()) {
            if (dto.getCourseId().toLowerCase().contains(searchText) ||
                    dto.getCourseName().toLowerCase().contains(searchText) ||
                    dto.getDescription().toLowerCase().contains(searchText) ||
                    dto.getDuration().toLowerCase().contains(searchText) ||
                    String.valueOf(dto.getFee()).toLowerCase().contains(searchText)) {
                filteredList.add(new CourseTM(
                        dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getDescription(),
                        dto.getDuration(),
                        dto.getFee()
                ));
            }
        }
        tblCourses.setItems(filteredList);
    }
    public void txtDurationKeyReleased(KeyEvent keyEvent) {}

    public void txtFeeKeyReleased(KeyEvent keyEvent) {
    }

}