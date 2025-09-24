package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_system.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Student courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Student courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String courseID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
