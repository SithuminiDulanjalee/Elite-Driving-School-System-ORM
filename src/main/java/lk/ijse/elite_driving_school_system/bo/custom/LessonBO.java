package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LessonBO extends SuperBO {
    void saveLesson(LessonDTO dto) throws SQLException, ClassNotFoundException;
    void updateLesson(LessonDTO dto) throws SQLException, ClassNotFoundException;
    void deleteLesson(LessonDTO dto)throws SQLException, ClassNotFoundException;
    LessonDTO getLesson(String lessonId)throws SQLException, ClassNotFoundException;
    List<LessonDTO> getAllLesson()throws SQLException, ClassNotFoundException;
    String generateNextLessonId()throws SQLException, ClassNotFoundException;

}
