package lk.ijse.elite_driving_school_system.dao.custom;

import lk.ijse.elite_driving_school_system.dao.CrudDAO;
import lk.ijse.elite_driving_school_system.dao.SuperDAO;
import lk.ijse.elite_driving_school_system.entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    Payment findById(String id);
    List<Payment> findAll();
    String generatePaymentId();
}
