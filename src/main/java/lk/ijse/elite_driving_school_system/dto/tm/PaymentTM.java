package lk.ijse.elite_driving_school_system.dto.tm;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentTM {
    private String paymentId;
    private String studentId;
    private String courseId;
    private Date paymentDate;
    private double amount;
    private String status;
}
