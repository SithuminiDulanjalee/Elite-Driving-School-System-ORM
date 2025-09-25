package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_system.dao.custom.PaymentDAO;
import lk.ijse.elite_driving_school_system.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public List<Payment> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Payment> payments = session.createQuery("FROM Payment", Payment.class).list();
        session.close();
        return payments;
    }


    @Override
    public String getLastId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Payment payment) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(payment);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment payment) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(payment);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Payment payment = session.get(Payment.class, id);
        if (payment != null) session.remove(payment);
        transaction.commit();
        session.close();
        return payment != null;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Payment findById(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Payment payment = session.get(Payment.class, id);
        session.close();
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Payment> list = session.createQuery("from Payment", Payment.class).list();
        session.close();
        return list;
    }

    @Override
    public String generatePaymentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC", String.class);
        query.setMaxResults(1);
        String lastId = query.uniqueResult();
        session.close();

        if (lastId == null) {
            return "PAY001";
        } else {
            int num = Integer.parseInt(lastId.replace("PAY", "")) + 1;
            return String.format("PAY%03d", num);
        }
    }
}
