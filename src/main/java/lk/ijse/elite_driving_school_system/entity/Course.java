package lk.ijse.elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseId;
    @Column(nullable = false)
    private String courseName;

    private String description;

    private String duration;
    @Column(nullable = false)
    private double fee;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "courses" , cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructors;

    public Course(String courseId, String courseName, String description, String duration, double fee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
        this.fee = fee;
    }

    public Course(String courseId) {
        this.courseId = courseId;
    }
}
