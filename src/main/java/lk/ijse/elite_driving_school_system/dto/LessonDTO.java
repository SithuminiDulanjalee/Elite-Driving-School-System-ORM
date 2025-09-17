package lk.ijse.elite_driving_school_system.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LessonDTO {
    private String lessonId;
    private String studentId;
    private String instructorId;
    private String courseId;
    private Date date;
    private String lessonName;
    private Time time;
    private String status;
}
