package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;

import java.sql.SQLException;
import java.util.Map;

public interface HomePageBO extends SuperBO {
    public int getStudentCount() throws SQLException, ClassNotFoundException ;

    public int getCourseCount() throws SQLException, ClassNotFoundException;

    public int getInstructorCount() throws SQLException, ClassNotFoundException;

    public Map<String, Integer> getStudentCountByDate() throws SQLException, ClassNotFoundException ;
}
