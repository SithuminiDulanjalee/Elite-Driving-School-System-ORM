package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_system.entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Lesson courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Lesson> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Lesson courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String courseID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
