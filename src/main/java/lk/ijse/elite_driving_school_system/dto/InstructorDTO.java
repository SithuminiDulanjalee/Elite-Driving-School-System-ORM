package lk.ijse.elite_driving_school_system.dto;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InstructorDTO {
    private String instructorId;
    private String instructorName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String specialization;
    private String availability;
    private Date dob;
    private Date registerDate;
    private ArrayList<CourseDTO> courses;
    private ArrayList<LessonDTO> lessons;
}
