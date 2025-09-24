package lk.ijse.elite_driving_school_system.dao;

import lk.ijse.elite_driving_school_system.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory==null)?new DAOFactory():daoFactory;
    }
    public enum DAOTypes {
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        STUDENT,
        USER,
        QUERY
    }
    public SuperDAO getDAO(DAOTypes daoType) {
        switch(daoType){
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

}
