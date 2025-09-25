package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_system.bo.util.EntityDtoConverter;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;
import lk.ijse.elite_driving_school_system.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final EntityDtoConverter converter = new EntityDtoConverter();

    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = converter.getStudent(dto);
        studentDAO.save(student);
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = converter.getStudent(dto);
        studentDAO.update(student);
        return false;
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        studentDAO.delete(studentId);
        return false;
    }

    @Override
    public StudentDTO getStudent(String studentId) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.getStudent(studentId);
        return converter.getStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : all) {
            dtos.add(converter.getStudentDTO(student));
        }
        return dtos;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        try {
            String lastId = studentDAO.getLastId();
            char tableChar = 'S';

            if (lastId != null && !lastId.isEmpty()) {
                String lastIdNumberString = lastId.substring(1);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                return String.format(tableChar + "%03d", nextIdNumber);
            }
            return tableChar + "001";
        } catch (Exception e) {
            throw new SQLException("Failed to generate next course ID", e);
        }
    }
}
