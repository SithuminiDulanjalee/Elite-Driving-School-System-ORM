package lk.ijse.elite_driving_school_system.entity;

import jakarta.persistence.*;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    private String instructorId;

    @Column(nullable = false)
    private String instructorName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "instructors", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "instructors", cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();
}
