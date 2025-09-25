package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_system.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_system.bo.exception.NotFoundException;
import lk.ijse.elite_driving_school_system.bo.util.EntityDtoConverter;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_system.dto.CourseDTO;
import lk.ijse.elite_driving_school_system.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final EntityDtoConverter converter = new EntityDtoConverter();

    @Override
    public ArrayList<CourseDTO> getAllCourses() throws SQLException, ClassNotFoundException {
        try {

            List<Course> courses = courseDAO.getAll();
            ArrayList<CourseDTO> courseDTOs = new ArrayList<>();

            for (Course course : courses) {
                courseDTOs.add(converter.getCourseDTO(course));
            }
            return courseDTOs;
        }
        catch (Exception e) {
            throw new SQLException("Failed to retrieve lessons", e);
        }
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        try {
            Optional<Course> optionalCourse = courseDAO.findById(courseDTO.getCourseId());
            if (optionalCourse.isPresent()) {
                throw new DuplicateException("Duplicate Course ID: " + courseDTO.getCourseId());
            }

            Course course = converter.getCourse(courseDTO);
            return courseDAO.save(course);

        } catch (DuplicateException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException("Failed to save course", e);
        }
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        try {
            Optional<Course> optionalCourse = courseDAO.findById(courseDTO.getCourseId());
            if (optionalCourse.isEmpty()) {
                throw new NotFoundException("Course not found with ID: " + courseDTO.getCourseId());
            }

            Course course = converter.getCourse(courseDTO);
            return courseDAO.update(course);

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException("Failed to update lesson", e);
        }
    }

    @Override
    public boolean deleteCourse(String id) throws SQLException, ClassNotFoundException {

        try {
            Optional<Course> optionalCourse = courseDAO.findById(id);
            if (optionalCourse.isEmpty()) {
                throw new NotFoundException("Course not found with ID: " + id);
            }

            return courseDAO.delete(id);

        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException("Failed to delete course", e);
        }
    }

    @Override
    public String generateNewCourseId() throws SQLException, ClassNotFoundException {
        return getNextId();
    }

    @Override
    public String getNextId() throws SQLException {
        try {
            String lastId = courseDAO.getLastId();
            char tableChar = 'C';

            if (lastId != null && !lastId.isEmpty()) {
                String lastIdNumberString = lastId.substring(1);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                return String.format(tableChar + "%03d", nextIdNumber);
            }
            return tableChar + "001";
        } catch (Exception e) {
            throw new SQLException("Failed to generate next course ID", e);
        }
    }
}
