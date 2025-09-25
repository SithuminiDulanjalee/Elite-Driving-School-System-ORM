package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_system.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        session.close();
        return students;
    }

    @Override
    public String getLastId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Student student) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(student);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String studentId = id;

            session.createQuery("DELETE FROM Lesson l WHERE l.students.studentId = :id")
                    .setParameter("id", studentId)
                    .executeUpdate();

            session.createQuery("DELETE FROM Payment p WHERE p.students.studentId = :id")
                    .setParameter("id", studentId)
                    .executeUpdate();

            session.createQuery("DELETE FROM Student s WHERE s.studentId = :id")
                    .setParameter("id", studentId)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Student getStudent(String studentId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = session.get(Student.class, studentId);
        session.close();
        return student;
    }

    @Override
    public int getStudentCount() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Integer> query = session.createQuery("SELECT COUNT(s) FROM Student s", Integer.class);
        Integer count = query.uniqueResult();
        session.close();
        return count;
    }

    @Override
    public Student findById(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return students;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery("SELECT s.studentId FROM Student s ORDER BY s.studentId DESC", String.class);
        query.setMaxResults(1);
        String lastId = query.uniqueResult();
        session.close();
        if (lastId != null) {
            int newId = Integer.parseInt(lastId.replace("S", "")) + 1;
            return String.format("S%03d", newId);
        } else {
            return "S001";
        }
    }
}
