package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.bo.util.EntityDtoConverter;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_system.dto.LessonDTO;
import lk.ijse.elite_driving_school_system.entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonBOImpl implements LessonBO {
    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);
    private final EntityDtoConverter entityDtoConverter = new EntityDtoConverter();

    @Override
    public boolean saveLesson(LessonDTO dto) throws SQLException, ClassNotFoundException, UserAlreadyExistsException {
        Lesson lesson = entityDtoConverter.getLesson(dto);
        lessonDAO.save(lesson);
        return false;
    }

    @Override
    public void updateLesson(LessonDTO dto) throws SQLException, ClassNotFoundException {
        Lesson lesson = entityDtoConverter.getLesson(dto);
        lessonDAO.update(lesson);
    }

    @Override
    public void deleteLesson(LessonDTO dto) throws SQLException, ClassNotFoundException {
        Lesson lesson = new Lesson();
        lesson.setLessonId(dto.getLessonId());
        lessonDAO.delete(lesson.getLessonId());
    }

    @Override
    public LessonDTO getLesson(String lessonId) throws SQLException, ClassNotFoundException {
        Lesson lesson = lessonDAO.getLesson(lessonId);
        return entityDtoConverter.getLessonDTO(lesson);
    }

    @Override
    public List<LessonDTO> getAllLesson() throws SQLException, ClassNotFoundException {
        List<Lesson> all = lessonDAO.getAll();
        List<LessonDTO> dtos = new ArrayList<>();
        for (Lesson lesson : all) {
            dtos.add(entityDtoConverter.getLessonDTO(lesson));
        }
        return dtos;
    }

    @Override
    public String generateNextLessonId() throws SQLException, ClassNotFoundException {
        String lastId = lessonDAO.getLastId();
        if (lastId != null) {
            int id = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("L%03d", id);
        } else {
            return "L001";
        }
    }
}
