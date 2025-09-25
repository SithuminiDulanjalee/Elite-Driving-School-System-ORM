package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CourseBO extends SuperBO {
    ArrayList<CourseDTO> getAllCourses() throws SQLException, ClassNotFoundException;
    boolean saveCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException;
    boolean updateCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCourse(String id) throws SQLException, ClassNotFoundException;
    String generateNewCourseId() throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException;
}
