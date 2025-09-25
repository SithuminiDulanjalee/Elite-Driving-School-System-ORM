package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.bo.exception.InvalidCredentialsException;
import lk.ijse.elite_driving_school_system.dto.UserDTO;

public interface LoginBO extends SuperBO {
    UserDTO getUser(String userName) throws InvalidCredentialsException;
}
