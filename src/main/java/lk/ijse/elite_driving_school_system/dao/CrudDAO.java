package lk.ijse.elite_driving_school_system.dao;

import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    List<T> getAll() throws SQLException;
    String getLastId() throws SQLException;
    boolean save(T t) throws SQLException, UserAlreadyExistsException;
    boolean update(T t) throws SQLException;
    boolean delete(String id) throws SQLException;
    List<String> getAllIds() throws SQLException;
}
