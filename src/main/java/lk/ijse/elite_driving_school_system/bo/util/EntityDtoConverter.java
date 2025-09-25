package lk.ijse.elite_driving_school_system.bo.util;

import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;
import lk.ijse.elite_driving_school_system.entity.Course;
import lk.ijse.elite_driving_school_system.entity.Instructor;
import lk.ijse.elite_driving_school_system.entity.Lesson;
import lk.ijse.elite_driving_school_system.entity.Student;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

}
