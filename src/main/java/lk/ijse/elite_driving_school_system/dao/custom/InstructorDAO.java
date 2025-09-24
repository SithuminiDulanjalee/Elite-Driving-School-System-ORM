package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.Instructor;

import java.sql.SQLException;
import java.util.Optional;

public interface InstructorDAO extends CrudDAO<Instructor> {
    Optional<Instructor> findById(String id) throws SQLException;
}
