package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_system.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_system.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Instructor> getAll() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Instructor> query = session.createQuery("FROM Instructor", Instructor.class);
            return query.list();
        } catch (Exception e) {
            throw new SQLException("Failed to get all Instructors", e);
        }
    }

    @Override
    public String getLastId() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<String> query = session.createQuery(
                    "SELECT i.instructorId FROM Instructor i ORDER BY i.instructorId DESC", String.class
            );
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Failed to get last instructor ID", e);
        }
    }

    @Override
    public boolean save(Instructor instructor) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            session.persist(instructor);
            return true;
        } catch (Exception e) {
            throw new SQLException("Failed to save instructor", e);
        }
    }

    @Override
    public boolean update(Instructor instructor) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            session.merge(instructor);
            return true;
        } catch (Exception e) {
            throw new SQLException("Failed to update instructor", e);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Instructor instructor = session.get(Instructor.class, id);
            if (instructor != null) {
                session.remove(instructor);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new SQLException("Failed to delete instructor", e);
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<String> query = session.createQuery(
                    "SELECT i.instructorId FROM Instructor i", String.class
            );
            return query.list();
        } catch (Exception e) {
            throw new SQLException("Failed to get all instructor IDs", e);
        }
    }

    @Override
    public Optional<Instructor> findById(String id) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Instructor instructor = session.get(Instructor.class, id);
            return Optional.ofNullable(instructor);
        } catch (Exception e) {
            throw new SQLException("Failed to find instructor by ID", e);
        }
    }
}
