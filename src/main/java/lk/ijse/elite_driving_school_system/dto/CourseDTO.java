package lk.ijse.elite_driving_school_system.dto;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    private String courseId;
    private String instructorId;
    private String courseName;
    private String duration;
    private double fee;
    private ArrayList<LessonDTO> lessons;
    private ArrayList<StudentCourseDetailDTO> studentCourseDetails;
}
