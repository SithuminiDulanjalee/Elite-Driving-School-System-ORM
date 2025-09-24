package lk.ijse.elite_driving_school_system.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InstructorTM {
    private String instructorId;
    private String instructorName;
    private String phone;
    private String email;
    private String specialization;
}
