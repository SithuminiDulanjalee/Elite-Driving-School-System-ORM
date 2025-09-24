package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_system.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(Payment courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Payment courseDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String courseID) throws SQLException, ClassNotFoundException {
        return false;
    }
}
