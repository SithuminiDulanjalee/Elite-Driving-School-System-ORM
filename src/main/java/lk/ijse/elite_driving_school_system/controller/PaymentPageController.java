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
import lk.ijse.elite_driving_school_system.bo.custom.PaymentBO;
import lk.ijse.elite_driving_school_system.dto.PaymentDTO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.tm.PaymentTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    public TextField txtId;
    public ComboBox<String> cmbStudent;
    public ComboBox<String> cmbCourse;
    public Button btnPay;
    public Button btnReset;
    public TableView<PaymentTM> tblPayment;
    public TableColumn<PaymentTM, String> colId;
    public TableColumn<PaymentTM, String> colStudent;
    public TableColumn<PaymentTM, String> colCourse;
    public TableColumn<PaymentTM, Date> colPayDate;
    public TableColumn<PaymentTM, Double> colAmount;
    public TableColumn<PaymentTM, String> colStatus;
    public TextField txtSearch;
    public DatePicker payDatePicker;
    public TextField txtAmount;
    public ComboBox<String> cmbStatus;

    private final PaymentBO paymentBO =
            (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    // ✅ keep class-level list to bind to TableView
    private final ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCellValueFactory();
            loadTableData();   // load table immediately
            loadStudentIds();
            loadCourseIds();
            loadNextId();

            cmbStatus.setItems(FXCollections.observableArrayList("Pending", "Completed"));
            btnPay.setDisable(false);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Initialization failed!").show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadTableData() {
        try {
            List<PaymentDTO> allPayments = paymentBO.getAllPayments();

            paymentList.clear(); // clear old data
            for (PaymentDTO dto : allPayments) {
                paymentList.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getStudentId(),
                        dto.getCourseId(),
                        dto.getPaymentDate(),
                        dto.getAmount(),
                        dto.getStatus()
                ));
            }

            tblPayment.setItems(paymentList);
            tblPayment.refresh();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payment data!").show();
        }
    }

    private void loadNextId() {
        txtId.setText(paymentBO.generatePaymentId());
        txtId.setEditable(false);
    }

    private void loadStudentIds() throws SQLException {
        List<StudentDTO> students = paymentBO.getAllStudents();
        cmbStudent.getItems().clear();
        for (StudentDTO s : students) {
            cmbStudent.getItems().add(s.getStudentId());
        }
    }

    private void loadCourseIds() throws SQLException {
        List<CourseDTO> courses = paymentBO.getAllPrograms();
        cmbCourse.getItems().clear();
        for (CourseDTO c : courses) {
            cmbCourse.getItems().add(c.getCourseId());
        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        try {
            if (cmbStudent.getValue() == null || cmbCourse.getValue() == null ||
                    payDatePicker.getValue() == null || cmbStatus.getValue() == null ||
                    txtAmount.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
                return;
            }

            PaymentDTO dto = new PaymentDTO(
                    txtId.getText(),
                    cmbStudent.getValue(),
                    cmbCourse.getValue(),
                    Date.valueOf(payDatePicker.getValue()),
                    Double.parseDouble(txtAmount.getText()),
                    cmbStatus.getValue()
            );

            if (paymentBO.savePayment(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Payment saved!").show();
                resetPage();
                loadTableData(); // refresh table
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving!").show();
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void resetPage() {
        txtAmount.clear();
        cmbStatus.setValue(null);
        payDatePicker.setValue(null);
        cmbStudent.setValue(null);
        cmbCourse.setValue(null);
        loadNextId();
        btnPay.setDisable(false);
        tblPayment.getSelectionModel().clearSelection();
    }

    public void tblPaymentOnClickAction(MouseEvent mouseEvent) {
        PaymentTM selected = tblPayment.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtId.setText(selected.getPaymentId());
            cmbStudent.setValue(selected.getStudentId());
            cmbCourse.setValue(selected.getCourseId());
            payDatePicker.setValue(selected.getPaymentDate().toLocalDate());
            txtAmount.setText(String.valueOf(selected.getAmount()));
            cmbStatus.setValue(selected.getStatus());

            btnPay.setDisable(true);
        }
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
        String searchText = txtSearch.getText().toLowerCase();

        if (searchText.isEmpty()) {
            tblPayment.setItems(paymentList); // reset to full
            return;
        }

        ObservableList<PaymentTM> filtered = FXCollections.observableArrayList();

        for (PaymentTM tm : paymentList) {
            if (tm.getPaymentId().toLowerCase().contains(searchText) ||
                    tm.getStudentId().toLowerCase().contains(searchText) ||
                    tm.getCourseId().toLowerCase().contains(searchText) ||
                    tm.getStatus().toLowerCase().contains(searchText) ||
                    String.valueOf(tm.getAmount()).contains(searchText)) {
                filtered.add(tm);
            }
        }

        tblPayment.setItems(filtered);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchKeyReleased(null);
    }
}
