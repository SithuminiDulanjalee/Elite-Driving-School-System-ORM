package lk.ijse.elite_driving_school_system.bo.custom;

import lk.ijse.elite_driving_school_system.bo.SuperBO;
import lk.ijse.elite_driving_school_system.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent();
}
