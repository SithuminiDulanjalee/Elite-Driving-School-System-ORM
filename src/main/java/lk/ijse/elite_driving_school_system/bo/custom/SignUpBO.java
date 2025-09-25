package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.bo.exception.UserAlreadyExistsException;
import lk.ijse.elite_driving_school_system.dto.UserDTO;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    void signUp(UserDTO userDTO) throws UserAlreadyExistsException, SQLException;
}
