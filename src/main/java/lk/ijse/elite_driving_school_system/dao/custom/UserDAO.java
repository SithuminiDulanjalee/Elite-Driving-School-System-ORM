package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User> {
    Optional<User> findById(String id) throws SQLException;
    User getUser(String userName)throws Exception;
    Optional<User> findByUsername(String username) throws Exception;
}
