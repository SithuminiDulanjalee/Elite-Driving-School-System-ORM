package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_system.entity.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Instructor courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Instructor> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Instructor courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String courseID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
