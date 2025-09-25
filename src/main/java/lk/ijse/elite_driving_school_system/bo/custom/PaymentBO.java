package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.PaymentDTO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO dto) throws SQLException, UserAlreadyExistsException;
    List<PaymentDTO> getAllPayments() throws SQLException;
    String generatePaymentId();
    List<StudentDTO> getAllStudents() throws SQLException;
    List<CourseDTO> getAllPrograms() throws SQLException;
    boolean updateStatus(String paymentId, String newStatus) throws Exception;
    StudentDTO findStudentById(String studentId) throws SQLException, ClassNotFoundException;
    CourseDTO findProgramById(String programId) throws SQLException;
    boolean updatePayment(PaymentDTO dto) throws SQLException;
    boolean deletePayment(String id) throws SQLException;
    PaymentDTO getPayment(String id);
}
