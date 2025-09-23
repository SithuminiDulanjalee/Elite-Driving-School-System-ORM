package lk.ijse.elite_driving_school_system.entity;

import jakarta.persistence.*;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;
import lk.ijse.elite_driving_school_system.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Date registerDate;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();
    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

}
