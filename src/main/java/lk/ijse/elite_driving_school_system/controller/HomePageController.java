package lk.ijse.elite_driving_school_system.controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.elite_driving_school_system.bo.BOFactory;
import lk.ijse.elite_driving_school_system.bo.custom.HomePageBO;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public Label lblStudents;
    public Label lblCourses;
    public Label lblInstructors;
    public BarChart<String, Number> barChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
