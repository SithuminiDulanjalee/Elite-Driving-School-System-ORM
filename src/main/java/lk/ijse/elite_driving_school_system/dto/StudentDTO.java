package lk.ijse.elite_driving_school_system.dto;

import lk.ijse.elite_driving_school_system.entity.Payment;
import lombok.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentDTO {
    private String studentId;
    private String studentName;
    private String phone;
    private String email;
    private String address;
    private Date registerDate;
    private String course;
    private double payment;

}
