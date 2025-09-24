package lk.ijse.elite_driving_school_system.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseTM {
    private String courseId;
    private String courseName;
    private String description;
    private String duration;
    private double fee;
}
