package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.HomePageBO;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_system.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_system.dao.custom.StudentDAO;

import java.sql.SQLException;
import java.util.Map;

public class HomePageBOImpl implements HomePageBO {

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int getCourseCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int getInstructorCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public Map<String, Integer> getStudentCountByDate() throws SQLException, ClassNotFoundException {
        return Map.of();
    }
}
