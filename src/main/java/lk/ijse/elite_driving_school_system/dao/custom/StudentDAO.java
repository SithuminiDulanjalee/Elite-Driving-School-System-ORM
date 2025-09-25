package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    Student getStudent(String studentId) throws SQLException,ClassNotFoundException;
    int getStudentCount()throws SQLException,ClassNotFoundException;
    Student findById(String id) throws SQLException,ClassNotFoundException;
    List<Student> findAll()throws SQLException,ClassNotFoundException;
    String generateNewId()throws SQLException,ClassNotFoundException;
}
