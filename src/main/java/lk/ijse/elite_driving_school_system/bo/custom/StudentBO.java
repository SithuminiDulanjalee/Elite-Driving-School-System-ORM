package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, UserAlreadyExistsException;
    boolean updateStudent(StudentDTO dto)throws SQLException,ClassNotFoundException;
    boolean deleteStudent(String dto)throws SQLException,ClassNotFoundException;
    StudentDTO getStudent(String studentId)throws SQLException,ClassNotFoundException;
    List<StudentDTO> getAllStudent()throws SQLException,ClassNotFoundException;
    String generateNewId()throws SQLException,ClassNotFoundException;
}
