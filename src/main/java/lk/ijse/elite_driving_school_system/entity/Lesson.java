package lk.ijse.elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    private String lessonId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student students;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructors;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courses;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String lessonName;

    @Column(nullable = false)
    private String time;
}
