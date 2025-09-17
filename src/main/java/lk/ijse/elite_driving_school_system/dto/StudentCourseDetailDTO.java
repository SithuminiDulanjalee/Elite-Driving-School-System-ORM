package lk.ijse.elite_driving_school_system.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentCourseDetailDTO {
    private String studentDetailId;
    private String studentId;
    private String courseId;
    private Date enrollDate;
    private String status;
}
