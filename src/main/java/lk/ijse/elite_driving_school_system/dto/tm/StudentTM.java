package lk.ijse.elite_driving_school_system.dto.tm;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentTM {
    private String studentId;
    private String studentName;
    private String phone;
    private String email;
    private String address;
    private Date registerDate;
    private String course;
    private double payment;

}
