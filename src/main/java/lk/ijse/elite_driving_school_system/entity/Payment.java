package lk.ijse.elite_driving_school_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student students;

    @Column(nullable = false)
    private Date paymentDate;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private String status;
}
