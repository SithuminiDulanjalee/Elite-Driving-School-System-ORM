package lk.ijse.elite_driving_school_system.dto;

import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.sql.Date;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String studentId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private Date dob;
    private Date registerDate;
    private ArrayList<StudentCourseDetailDTO> studentCourseDetails;
    private ArrayList<LessonDTO> lessons;
    private ArrayList<PaymentDTO> payments;

}
