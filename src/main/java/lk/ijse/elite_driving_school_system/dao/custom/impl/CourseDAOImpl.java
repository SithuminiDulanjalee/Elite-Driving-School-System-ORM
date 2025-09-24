package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_system.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Course courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Course> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Course courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String courseID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
