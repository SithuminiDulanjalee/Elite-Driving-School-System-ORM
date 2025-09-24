package lk.ijse.elite_driving_school_system.bo;

import lk.ijse.elite_driving_school_system.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }
    public enum BOTypes{
        COURSE,
        HOME_PAGE,
        INSTRUCTOR,
        LESSON,
        LOGIN,
        PAYMENT,
        SIGN_UP,
        STUDENT,
        USER
    }
    public SuperBO getBO(BOTypes boType) {
        switch(boType){
            case COURSE:
                return new CourseBOImpl();
            case HOME_PAGE:
                return new HomePageBOImpl();
            case INSTRUCTOR:
                return new InstructorBOImpl();
            case LESSON:
                return new LessonBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case SIGN_UP:
                return new SignUpBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
