package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.Lesson;

import java.sql.SQLException;

public interface LessonDAO extends CrudDAO<Lesson> {
    public Lesson getLesson(String lessonId) throws SQLException, ClassNotFoundException;
}
