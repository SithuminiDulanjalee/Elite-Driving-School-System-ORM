package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.LoginBO;
import lk.ijse.elite_driving_school_system.bo.exception.InvalidCredentialsException;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_system.dao.custom.UserDAO;
import lk.ijse.elite_driving_school_system.dto.UserDTO;
import lk.ijse.elite_driving_school_system.entity.User;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public UserDTO getUser(String userName) throws InvalidCredentialsException {
        try {
            User user = userDAO.getUser(userName);
            return new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getRole());
        } catch (Exception e) {
            throw new InvalidCredentialsException(e.getMessage());
        }
    }
}
