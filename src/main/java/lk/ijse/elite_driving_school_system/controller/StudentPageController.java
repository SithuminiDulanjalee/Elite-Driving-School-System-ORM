package lk.ijse.elite_driving_school_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.elite_driving_school_system.bo.BOFactory;
import lk.ijse.elite_driving_school_system.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_system.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;
import lk.ijse.elite_driving_school_system.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_system.dto.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtAddress;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<StudentTM> tblStudent;
    public TableColumn<StudentTM, String> colId;
    public TableColumn<StudentTM, String> colName;
    public TableColumn<StudentTM, String> colPhone;
    public TableColumn<StudentTM, String> colEmail;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, Date> colRegistrationDate;
    public TableColumn<StudentTM, String> colCourse;
    public DatePicker registrationDatePicker;
    public ComboBox<String> cmbCourse;
    public TableColumn<StudentTM,Double> colPayment;
    public TextField txtSearch;
    public TextField txtPayment;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private final String namePattern = "^[A-Za-z ]+$";
    private final String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCellValueFactory();
            loadAllStudent();
            loadCourseIds();
            generateStudentId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    private void loadAllStudent() throws SQLException, ClassNotFoundException {
        List<StudentDTO> allStudent = studentBO.getAllStudent();
        ObservableList<StudentTM> studentTms = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : allStudent) {
            studentTms.add(new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getStudentName(),
                    studentDTO.getPhone(),
                    studentDTO.getEmail(),
                    studentDTO.getAddress(),
                    studentDTO.getRegisterDate(),
                    studentDTO.getCourse(),
                    studentDTO.getPayment()
            ));
        }
        tblStudent.setItems(studentTms);
    }


    private void loadCourseIds() {
        try {
            List<CourseDTO> allCourses = courseBO.getAllCourses();
            ObservableList<String> courseIds = FXCollections.observableArrayList();
            for (CourseDTO course : allCourses) {
                courseIds.add(course.getCourseId());
            }
            cmbCourse.setItems(courseIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        resetPage();
        generateStudentId();
    }

    public void tblStudentOnClickAction(MouseEvent mouseEvent) {
        StudentTM selected = tblStudent.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtStudentId.setText(selected.getStudentId());
            txtName.setText(selected.getStudentName());
            txtPhone.setText(selected.getPhone());
            txtEmail.setText(selected.getEmail());
            txtAddress.setText(selected.getAddress());
            registrationDatePicker.setValue(selected.getRegisterDate().toLocalDate());
            cmbCourse.setValue(selected.getCourse());
            txtPayment.setText(String.valueOf(selected.getPayment()));

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText().trim();
        if(id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Id can not be empty").show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete student " + id + "?");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (studentBO.deleteStudent(id)) {
                    new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete student").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
                e.printStackTrace();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(contactPattern);
        boolean isValidName = name.matches(namePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");
        if(!isValidEmail)txtEmail.setStyle(txtEmail.getStyle()+"-fx-border-color: red;");
        if(!isValidPhone)txtPhone.setStyle(txtPhone.getStyle()+"-fx-border-color: red;");

        if (!(isValidName&& isValidEmail&&isValidPhone)) {
            new Alert(Alert.AlertType.WARNING, "Fail to save student").show();
            return;
        }

        try {
            StudentDTO dto = new StudentDTO(
                    txtStudentId.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    Date.valueOf(registrationDatePicker.getValue()),
                    cmbCourse.getValue() != null ? cmbCourse.getValue() : null,
                    Double.parseDouble(txtPayment.getText())
            );
            if (studentBO.updateStudent(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to update student").show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(contactPattern);
        boolean isValidName = name.matches(namePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");
        if(!isValidEmail)txtEmail.setStyle(txtEmail.getStyle()+"-fx-border-color: red;");
        if(!isValidPhone)txtPhone.setStyle(txtPhone.getStyle()+"-fx-border-color: red;");

        if (!(isValidName&& isValidEmail&&isValidPhone)) {
            new Alert(Alert.AlertType.WARNING, "Fail to save student").show();
            return;
        }

        try {
            StudentDTO dto = new StudentDTO(
                    txtStudentId.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText(),
                    Date.valueOf(registrationDatePicker.getValue()),
                    cmbCourse.getValue() != null ? cmbCourse.getValue() : null,
                    Double.parseDouble(txtPayment.getText())
            );
            if (studentBO.saveStudent(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to save student").show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail").show();
        }
    }

//    private void loadNextId() throws SQLException, ClassNotFoundException {
//        try {
//            txtStudentId.setText(studentBO.generateNewId());
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Failed to generate ID: " + e.getMessage()).show();
//            e.printStackTrace();
//        }
//
//    }

    private void resetPage() {
        try {
            loadAllStudent();
            generateStudentId();
            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
//            txtId.clear();
            txtName.clear();
            txtPhone.clear();
            txtEmail.clear();
            txtAddress.clear();
            registrationDatePicker.setValue(null);
            cmbCourse.setValue(null);
            txtPayment.clear();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong..").show();
        }
    }

    private void generateStudentId() throws SQLException, ClassNotFoundException {
        String newId = studentBO.generateNewId();
        txtStudentId.setText(newId);
        txtStudentId.setEditable(false);
    }


    public void txtAddressKeyReleased(KeyEvent keyEvent) {
    }

    public void txtEmailKeyReleased(KeyEvent keyEvent) {
    }

    public void txtPhoneKeyReleased(KeyEvent keyEvent) {
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String searchText = txtSearch.getText().toLowerCase();
        ObservableList<StudentTM> allStudents = FXCollections.observableArrayList();
        ObservableList<StudentTM> filteredList = FXCollections.observableArrayList();

        for (StudentDTO dto : studentBO.getAllStudent()) {
            if (dto.getStudentId().toLowerCase().contains(searchText) ||
                    dto.getStudentName().toLowerCase().contains(searchText) ||
                    dto.getPhone().toLowerCase().contains(searchText) ||
                    dto.getEmail().toLowerCase().contains(searchText) ||
                    dto.getAddress().toLowerCase().contains(searchText) ||
                    String.valueOf(dto.getRegisterDate()).toLowerCase().contains(searchText) ||
                    dto.getCourse().toLowerCase().contains(searchText) ||
                    String.valueOf(dto.getPayment()).toLowerCase().contains(searchText)) {
                filteredList.add(new StudentTM(
                        dto.getStudentId(),
                        dto.getStudentName(),
                        dto.getPhone(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getRegisterDate(),
                        dto.getCourse(),
                        dto.getPayment()
                ));
            }
        }
        tblStudent.setItems(filteredList);
    }
}
