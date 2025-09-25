package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_system.dao.custom.UserDAO;
import lk.ijse.elite_driving_school_system.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        } catch (Exception e) {
            throw new SQLException("Failed to get all users", e);
        }
    }

    @Override
    public String getLastId() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<String> query = session.createQuery(
                    "SELECT u.userId FROM User u ORDER BY u.userId DESC", String.class
            );
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Failed to get last user ID", e);
        }
    }

    @Override
    public boolean save(User user) throws SQLException, UserAlreadyExistsException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(user);
        } catch (Exception e){
            throw new UserAlreadyExistsException(e.getMessage());
        }


        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            // Hash password only if it is not already hashed
            if (!user.getPassword().startsWith("$2a$")) {
                String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                user.setPassword(hashedPassword);
            }

            session.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            throw new SQLException("Failed to update user", e);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            throw new SQLException("Failed to delete user", e);
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<String> query = session.createQuery(
                    "SELECT u.userId FROM User u", String.class
            );
            return query.list();
        } catch (Exception e) {
            throw new SQLException("Failed to get all user IDs", e);
        }
    }

    @Override
    public Optional<User> findById(String id) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            throw new SQLException("Failed to find user by ID", e);
        }
    }

    @Override
    public User getUser(String userName) throws Exception {
        User user = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User u WHERE u.userName = :userName";
        user = session.createQuery(hql, User.class)
                .setParameter("userName", userName)
                .uniqueResult();

        transaction.commit();
        session.close();

        return user;
    }

    public Optional<User> findByUsername(String username) throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery("FROM User u WHERE u.userName = :username", User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
