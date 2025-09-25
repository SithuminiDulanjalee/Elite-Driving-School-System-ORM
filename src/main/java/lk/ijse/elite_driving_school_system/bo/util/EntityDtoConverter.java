package lk.ijse.elite_driving_school_system.bo.util;

import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_system.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_system.dto.*;
import lk.ijse.elite_driving_school_system.entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntityDtoConverter {
    public InstructorDTO getInstructorDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getInstructorId(),
                instructor.getInstructorName(),
                instructor.getPhone(),
                instructor.getEmail(),
                instructor.getSpecialization()
        );
    }

    public Instructor getInstructor(InstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(dto.getInstructorId());
        instructor.setInstructorName(dto.getInstructorName());
        instructor.setPhone(dto.getEmail());
        instructor.setEmail(dto.getPhone());
        instructor.setSpecialization(dto.getSpecialization());
        return instructor;
    }

    public CourseDTO getCourseDTO(Course course) {
        return new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getDescription(),
                course.getDuration(),
                course.getFee()
        );
    }

    public Course getCourse(CourseDTO dto) {
        Course course = new Course();
        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setFee(dto.getFee());
        return course;
    }

    public LessonDTO getLessonDTO(Lesson lesson) {
        return new LessonDTO(
                lesson.getLessonId(),
                lesson.getStudents().getStudentId(),
                lesson.getInstructors().getInstructorId(),
                lesson.getCourses().getCourseId(),
                lesson.getDate(),
                lesson.getLessonName(),
                lesson.getTime().toString()
        );
    }

    public Lesson getLesson(LessonDTO dto) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(dto.getLessonId());
        lesson.setStudents(new Student(dto.getStudentId()));
        lesson.setInstructors(new Instructor(dto.getInstructorId()));
        lesson.setCourses(new Course(dto.getCourseId()));

        lesson.setDate(dto.getDate());
        lesson.setLessonName(dto.getLessonName());
        lesson.setTime(dto.getTime());

        return lesson;
    }

    public UserDTO getUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getRole()
        );
    }

    public User getUser(UserDTO dto) {
        if (dto == null) return null;
        return new User(
                dto.getUserId(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getRole()
        );
    }

    public Student getStudent(StudentDTO dto) {
        return new Student(
                dto.getStudentId(),
                dto.getStudentName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getRegisterDate(),
                dto.getCourse(),
                dto.getPayment()
        );
    }

    public StudentDTO getStudentDTO(Student entity) {
        return new StudentDTO(
                entity.getStudentId(),
                entity.getStudentName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getRegisterDate(),
                entity.getCourse(),
                entity.getPayment()
        );
    }

    public Payment getPayment(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setPaymentId(dto.getPaymentId());
        payment.setStudents(new Student(dto.getStudentId()));
        payment.setCourse(new Course(dto.getCourseId()));
        payment.setPaymentDate(Date.valueOf(String.valueOf(dto.getPaymentDate())));
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());

        return payment;
    }

    public PaymentDTO getPaymentDTO(Payment entity) {
        return new PaymentDTO(
                entity.getPaymentId(),
                entity.getStudents().getStudentId(),
                entity.getCourse().getCourseId(),
                entity.getPaymentDate(),
                entity.getAmount(),
                entity.getStatus()
        );
    }

}
