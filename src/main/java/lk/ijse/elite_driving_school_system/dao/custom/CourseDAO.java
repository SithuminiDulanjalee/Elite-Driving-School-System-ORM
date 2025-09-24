package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface CourseDAO extends CrudDAO<Course> {
    Optional<Course> findById(String courseId) throws SQLException;
}
