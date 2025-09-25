package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.PaymentBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.bo.util.EntityDtoConverter;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_system.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_system.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.PaymentDTO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;
import lk.ijse.elite_driving_school_system.entity.Course;
import lk.ijse.elite_driving_school_system.entity.Payment;
import lk.ijse.elite_driving_school_system.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private EntityDtoConverter converter = new EntityDtoConverter();
    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, UserAlreadyExistsException {
        return paymentDAO.save(converter.getPayment(dto));
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws SQLException {
        List<Payment> payments = paymentDAO.findAll();
        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : payments) {
            dtos.add(converter.getPaymentDTO(payment));
        }
        return dtos;
    }


    @Override
    public String generatePaymentId() {
        return paymentDAO.generatePaymentId();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(converter.getStudentDTO(student));
        }
        return dtos;
    }

    @Override
    public List<CourseDTO> getAllPrograms() throws SQLException {
        List<Course> courses = courseDAO.getAll();
        List<CourseDTO> dtos = new ArrayList<>();
        for (Course course : courses) {
            dtos.add(converter.getCourseDTO(course));
        }
        return dtos;
    }

    @Override
    public boolean updateStatus(String paymentId, String newStatus) throws Exception {
        Payment payment = paymentDAO.findById(paymentId);
        if (payment != null) {
            payment.setStatus(newStatus);
            return paymentDAO.update(payment);
        }
        return false;
    }

    @Override
    public StudentDTO findStudentById(String studentId) throws SQLException, ClassNotFoundException {
        Student s = studentDAO.findById(studentId);
        if (s == null) return null;
        return new StudentDTO(
                s.getStudentId(),
                s.getStudentName(),
                s.getPhone(),
                s.getEmail(),
                s.getAddress(),
                s.getRegisterDate(),
                null,
                s.getPayment()
        );
    }

    @Override
    public CourseDTO findProgramById(String programId) throws SQLException {
        Optional<Course> c = courseDAO.findById(programId);
        if (c.isEmpty()) return null;
        return new CourseDTO(
                c.get().getCourseId(),
                c.get().getCourseName(),
                c.get().getDescription(),
                c.get().getDuration(),
                c.get().getFee()
        );
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException {
        return paymentDAO.update(converter.getPayment(dto));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO getPayment(String id) {
        Payment payment = paymentDAO.findById(id);
        return payment != null ? converter.getPaymentDTO(payment) : null;
    }
}
