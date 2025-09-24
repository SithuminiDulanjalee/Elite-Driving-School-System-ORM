package lk.ijse.elite_driving_school_system.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public String getNextId() throws SQLException, ClassNotFoundException ;

    public boolean save(T courseDTO) throws SQLException, ClassNotFoundException;

    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    public boolean update(T courseDTO) throws SQLException, ClassNotFoundException ;

    public boolean delete(String courseID) throws SQLException, ClassNotFoundException ;
}
