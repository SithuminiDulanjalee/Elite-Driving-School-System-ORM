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
import lk.ijse.elite_driving_school_system.bo.custom.UserBO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.UserDTO;
import lk.ijse.elite_driving_school_system.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_system.dto.tm.UserTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
    public TableView<UserTM> tblUsers;
    public TableColumn<UserTM, String> colId;
    public TableColumn<UserTM, String> colName;
    public TableColumn<UserTM, String> colPassword;
    public TableColumn<UserTM, String> colRole;
    public TextField txtSearch;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    private final String namePattern = "^[A-Za-z ]+$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }

    }

    private void resetPage() {

        setCellValueFactory();
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText("");
            txtPassword.setText("");
            txtRole.setText("");

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadTableData() {
        try {
            ObservableList<UserTM> obList = FXCollections.observableArrayList();
            for (UserDTO dto : userBO.getAllUsers()) {
                obList.add(new UserTM(
                        dto.getUserId(),
                        dto.getUserName(),
                        dto.getPassword(),
                        dto.getRole()
                ));
            }
            tblUsers.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load user: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void loadNextId() {
        try {
            txtId.setText(userBO.getNextId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void tblUserOnClickAction(MouseEvent mouseEvent) {
        UserTM selected = tblUsers.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtId.setText(selected.getUserId());
            txtName.setText(selected.getUserName());
            txtPassword.setText(selected.getPassword());
            txtRole.setText(selected.getRole());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String searchText = txtSearch.getText().toLowerCase();
        ObservableList<UserTM> allUsers = FXCollections.observableArrayList();
        ObservableList<UserTM> filteredList = FXCollections.observableArrayList();

        for (UserDTO dto : userBO.getAllUsers()) {
            if (dto.getUserId().toLowerCase().contains(searchText) ||
                    dto.getUserName().toLowerCase().contains(searchText) ||
                    dto.getPassword().toLowerCase().contains(searchText) ||
                    dto.getRole().toLowerCase().contains(searchText)) {
                filteredList.add(new UserTM(
                        dto.getUserId(),
                        dto.getUserName(),
                        dto.getPassword(),
                        dto.getRole()
                ));
            }
        }
        tblUsers.setItems(filteredList);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void txtDurationKeyReleased(KeyEvent keyEvent) {
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
            UserDTO dto = new UserDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtPassword.getText(),
                    txtRole.getText()
            );
            if (userBO.saveUsers(dto)){
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to save user").show();
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
            new Alert(Alert.AlertType.WARNING, "Fail to update user").show();
            return;
        }

        try {
            UserDTO dto = new UserDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtPassword.getText(),
                    txtRole.getText()
            );
            if (userBO.updateUsers(dto)){
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully").show();
                resetPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Fail to update user").show();
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
                "Are you sure you want to delete user " + id + "?");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                if (userBO.deleteUsers(id)) {
                    new Alert(Alert.AlertType.INFORMATION, "User deleted successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete user").show();
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
}
