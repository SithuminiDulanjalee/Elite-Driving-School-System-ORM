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
import lk.ijse.elite_driving_school_system.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.dto.tm.InstructorTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    public TableView<InstructorTM> tblInstructor;
    public TableColumn<InstructorTM, String> colId;
    public TableColumn<InstructorTM, String> colName;
    public TableColumn<InstructorTM, String> colPhone;
    public TableColumn<InstructorTM, String> colEmail;
    public TableColumn<InstructorTM, String> colSpecialization;
    public TextField txtSearch;

    InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);
    private final String namePattern = "^[A-Za-z ]+$";
    private final String contactPattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCellValueFactory();
            loadTableData();

            txtInstructorId.setText(instructorBO.getNextId());
            txtInstructorId.setEditable(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resetPage();
    }

    private void resetPage() {

        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtEmail.setText("");
            txtName.setText("");
            txtPhone.setText("");
            txtSpecialization.setText("");

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    private void setCellValueFactory() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        loadTableData();

        txtInstructorId.setText(instructorBO.getNextId());
        txtInstructorId.setEditable(false);
    }

    private void loadTableData() {
        try {
            ObservableList<InstructorTM> obList = FXCollections.observableArrayList();
            for (InstructorDTO dto : instructorBO.getAllInstructors()) {
                obList.add(new InstructorTM(
                        dto.getInstructorId(),
                        dto.getInstructorName(),
                        dto.getPhone(),
                        dto.getEmail(),
                        dto.getSpecialization()
                ));
            }
            tblInstructor.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load instructor: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void loadNextId() {
        try {
            txtInstructorId.setText(instructorBO.getNextId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(contactPattern);
        boolean isValidEmail = email.matches(emailPattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");
        if(!isValidEmail)txtEmail.setStyle(txtEmail.getStyle()+"-fx-border-color: red;");
        if(!isValidPhone)txtPhone.setStyle(txtPhone.getStyle()+"-fx-border-color: red;");

        if (!(isValidName &&isValidEmail&&isValidPhone)) {
            new Alert(Alert.AlertType.WARNING, "Fail to save instructor").show();
            return;
        }

        try {
            InstructorDTO dto = new InstructorDTO(
                    txtInstructorId.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtSpecialization.getText()
            );
            if (instructorBO.saveInstructors(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Instructor saved successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to save instructor").show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(contactPattern);
        boolean isValidEmail = email.matches(emailPattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: #7367F0;");

        if(!isValidName)txtName.setStyle(txtName.getStyle()+"-fx-border-color: red;");
        if(!isValidEmail)txtEmail.setStyle(txtEmail.getStyle()+"-fx-border-color: red;");
        if(!isValidPhone)txtPhone.setStyle(txtPhone.getStyle()+"-fx-border-color: red;");

        if (!(isValidName &&isValidEmail&&isValidPhone)) {
            new Alert(Alert.AlertType.WARNING, "Fail to save instructor").show();
            return;
        }

        try {
            InstructorDTO dto = new InstructorDTO(
                    txtInstructorId.getText(),
                    txtName.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtSpecialization.getText()
            );
            if (instructorBO.updateInstructors(dto)){
                new Alert(Alert.AlertType.INFORMATION, "Instructor updated successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to update instructor").show();
            }
        }
        catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail").show();
            e.printStackTrace();
        }


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtInstructorId.getText().trim();
        if(id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Id can not be empty").show();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete instructor " + id + "?");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (instructorBO.deleteInstructors(id)) {
                    new Alert(Alert.AlertType.INFORMATION, "Instructor deleted successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete instructor").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
                e.printStackTrace();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void tblInstructorOnClickAction(MouseEvent mouseEvent) {
        InstructorTM selected = tblInstructor.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtInstructorId.setText(selected.getInstructorId());
            txtName.setText(selected.getInstructorName());
            txtPhone.setText(selected.getPhone());
            txtEmail.setText(selected.getEmail());
            txtSpecialization.setText(selected.getSpecialization());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }

    public void txtPhoneKeyReleased(KeyEvent keyEvent) {
    }

    public void txtEmailKeyReleased(KeyEvent keyEvent) {
    }
}
