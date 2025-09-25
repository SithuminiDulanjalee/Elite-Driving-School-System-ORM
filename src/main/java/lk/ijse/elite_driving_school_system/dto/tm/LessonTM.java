package lk.ijse.elite_driving_school_system.dto.tm;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LessonTM {
    private String lessonId;
    private String studentId;
    private String instructorId;
    private String courseId;
    private LocalDate date;
    private String lessonName;
    private String time;
}
