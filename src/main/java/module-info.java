module lk.ijse.elitedrivingschool.elite_driving_school_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires org.hibernate.orm.core;


    opens lk.ijse.elite_driving_school_system to javafx.fxml;
    exports lk.ijse.elite_driving_school_system;
}