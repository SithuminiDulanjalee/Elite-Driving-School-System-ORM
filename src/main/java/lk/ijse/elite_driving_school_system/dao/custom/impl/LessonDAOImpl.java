package lk.ijse.elite_driving_school_system.dao.custom.impl;

import lk.ijse.elite_driving_school_system.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_system.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_system.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImpl implements LessonDAO {

    @Override
    public List<Lesson> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<Lesson> lessons = session.createQuery("from Lesson", Lesson.class).list();
        tx.commit();
        session.close();
        return lessons;
    }

    @Override
    public String getLastId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        String hql = "SELECT l.lessonId FROM Lesson l ORDER BY l.lessonId DESC";
        Query<String> query = session.createQuery(hql, String.class);
        query.setMaxResults(1);
        String lastId = query.uniqueResult();
        tx.commit();
        session.close();
        return lastId;
    }

    @Override
    public boolean save(Lesson lesson) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(lesson);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Lesson lesson) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(lesson);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, id);
        if (lesson != null) {
            session.remove(lesson);
            tx.commit();
            session.close();
            return true;
        }
        tx.rollback();
        session.close();
        return false;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Lesson getLesson(String lessonId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, lessonId);
        tx.commit();
        session.close();
        return lesson;
    }
}
