module lk.ijse.elitedrivingschool.elite_driving_school_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens lk.ijse.elite_driving_school_system to javafx.fxml;


    opens lk.ijse.elite_driving_school_system.controller to javafx.fxml;


    opens lk.ijse.elite_driving_school_system.entity to org.hibernate.orm.core, jakarta.persistence;

    opens lk.ijse.elite_driving_school_system.dto to javafx.fxml, javafx.base;
    opens lk.ijse.elite_driving_school_system.dto.tm to javafx.base;

    exports lk.ijse.elite_driving_school_system;
    exports lk.ijse.elite_driving_school_system.controller;
    exports lk.ijse.elite_driving_school_system.dto;
    exports lk.ijse.elite_driving_school_system.dto.tm;
}