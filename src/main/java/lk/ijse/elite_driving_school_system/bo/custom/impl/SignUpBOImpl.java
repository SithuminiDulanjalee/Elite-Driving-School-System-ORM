package lk.ijse.elite_driving_school_system.bo.custom.impl;

import lk.ijse.elite_driving_school_system.bo.custom.SignUpBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.dao.DAOFactory;
import lk.ijse.elite_driving_school_system.dao.custom.UserDAO;
import lk.ijse.elite_driving_school_system.dto.UserDTO;
import lk.ijse.elite_driving_school_system.entity.User;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public void signUp(UserDTO userDTO) throws UserAlreadyExistsException, SQLException {
        User user = new User(userDTO.getUserId(),userDTO.getUserName(), userDTO.getPassword(), userDTO.getRole());
        user.setUserId(user.getUserId());
        userDAO.save(user);

    }
}
