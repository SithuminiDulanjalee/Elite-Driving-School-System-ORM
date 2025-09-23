package lk.ijse.elite_driving_school_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {
    public TextField txtId;
    public ComboBox cmbStudent;
    public ComboBox cmbCourse;
    public Button btnPay;
    public Button btnReset;
    public TableView tblPayment;
    public TableColumn colId;
    public TableColumn colStudent;
    public TableColumn colCourse;
    public TableColumn colPayDate;
    public TableColumn colAmount;
    public TableColumn colStatus;
    public TableColumn colTel11;
    public TextField txtSearch;
    public DatePicker payDatePicker;
    public TextField txtAmount;
    public TextField txtStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnPayOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void tblPaymentOnClickAction(MouseEvent mouseEvent) {
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
