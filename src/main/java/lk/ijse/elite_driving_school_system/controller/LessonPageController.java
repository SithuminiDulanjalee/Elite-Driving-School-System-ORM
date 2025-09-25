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
import lk.ijse.elite_driving_school_system.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_system.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_system.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;
import lk.ijse.elite_driving_school_system.dto.tm.LessonTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPageController implements Initializable {
    public TextField txtId;
    public TextField txtName;
    public ComboBox<String> cmbStudent;
    public ComboBox<String> cmbInstructor;
    public ComboBox<String> cmbCourse;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<LessonTM> tblLesson;
    public TableColumn<LessonTM, String> colId;
    public TableColumn<LessonTM, String> colName;
    public TableColumn<LessonTM, String> colStudent;
    public TableColumn<LessonTM, String> colInstructor;
    public TableColumn<LessonTM, String> colCourse;
    public TableColumn<LessonTM, LocalDate> colDate;
    public TableColumn<LessonTM, String> colTime;
    public TextField txtSearch;
    public DatePicker dateDatePicker;
    public TextField txtTime;

    LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LESSON);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtId.setText(lessonBO.generateNextLessonId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setCellValueFactory();
        try {
            loadAllLesson();
            loadComboBoxes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String searchText = txtSearch.getText().toLowerCase();


        List<LessonDTO> allLesson = lessonBO.getAllLesson();
        ObservableList<LessonTM> filteredList = FXCollections.observableArrayList();

        for (LessonDTO dto : allLesson) {
            if (dto.getLessonId().toLowerCase().contains(searchText) ||
                    dto.getStudentId().toLowerCase().contains(searchText) ||
                    dto.getInstructorId().toLowerCase().contains(searchText)||
                    dto.getCourseId().toLowerCase().contains(searchText) ||
                    String.valueOf(dto.getDate()).toLowerCase().contains(searchText) ||
                    dto.getLessonName().toLowerCase().contains(searchText) ||
                    dto.getTime().toLowerCase().contains(searchText)) {

                filteredList.add(new LessonTM(
                        dto.getLessonId(),
                        dto.getStudentId(),
                        dto.getInstructorId(),
                        dto.getCourseId(),
                        dto.getDate(),
                        dto.getLessonName(),
                        dto.getTime()
                ));
            }
        }

        tblLesson.setItems(filteredList);
    }

    public void tblLessonOnClickAction(MouseEvent mouseEvent) {
        LessonTM selected = tblLesson.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtId.setText(selected.getLessonId());
            cmbStudent.setValue(selected.getStudentId());
            cmbInstructor.setValue(selected.getInstructorId());
            cmbCourse.setValue(selected.getCourseId());
            dateDatePicker.setValue(selected.getDate());
            txtName.setText(selected.getLessonName());
            txtTime.setText(String.valueOf(selected.getTime()));
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        clearData();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lessonBO.deleteLesson(getObject());
        clearData();
        loadAllLesson();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lessonBO.updateLesson(getObject());
        clearData();
        loadAllLesson();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LessonDTO lessonDTO = getObject();

        try {
            boolean isSaved = lessonBO.saveLesson(lessonDTO);

            if (isSaved) {
                clearData();
                new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save lesson").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colName.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

    }

    private void loadAllLesson() throws SQLException, ClassNotFoundException {
        List<LessonDTO> allLesson = lessonBO.getAllLesson();
        ObservableList<LessonTM> lessonTms = FXCollections.observableArrayList();
        for (LessonDTO dto : allLesson) {
            lessonTms.add(new LessonTM(
                    dto.getLessonId(),
                    dto.getStudentId(),
                    dto.getInstructorId(),
                    dto.getCourseId(),
                    dto.getDate(),
                    dto.getLessonName(),
                    dto.getTime()
            ));
        }
        tblLesson.setItems(lessonTms);
    }

    private void loadComboBoxes() throws SQLException, ClassNotFoundException {
        List<StudentDTO> students = studentBO.getAllStudent();
        List<CourseDTO> courses = courseBO.getAllCourses();
        List<InstructorDTO> instructors = instructorBO.getAllInstructors();

        for (StudentDTO s : students) cmbStudent.getItems().add(s.getStudentId());
        for (CourseDTO c : courses) cmbCourse.getItems().add(c.getCourseId());
        for (InstructorDTO i : instructors) cmbInstructor.getItems().add(i.getInstructorId());
    }

    private LessonDTO getObject() {
        return new LessonDTO(
                txtId.getText(),
                cmbStudent.getValue(),
                cmbInstructor.getValue(),
                cmbCourse.getValue(),
                dateDatePicker.getValue(),
                txtName.getText(),
                txtTime.getText()
        );
    }

    private void clearData() throws SQLException, ClassNotFoundException {
        txtId.setText(lessonBO.generateNextLessonId());
        dateDatePicker.setValue(null);
        cmbStudent.getSelectionModel().clearSelection();
        cmbCourse.getSelectionModel().clearSelection();
        cmbInstructor.getSelectionModel().clearSelection();
        txtTime.clear();
        txtName.clear();
    }
}
