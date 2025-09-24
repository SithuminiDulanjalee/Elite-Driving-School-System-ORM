package lk.ijse.elite_driving_school_system.util;

import lk.ijse.elite_driving_school_system.dto.InstructorDTO;
import lk.ijse.elite_driving_school_system.entity.Instructor;

public class EntityDtoConverter {
    public InstructorDTO getInstructorDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getInstructorId(),
                instructor.getInstructorName(),
                instructor.getPhone(),
                instructor.getEmail(),
                instructor.getSpecialization()
        );
    }

    public Instructor getInstructor(InstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(dto.getInstructorId());
        instructor.setInstructorName(dto.getInstructorName());
        instructor.setPhone(dto.getEmail());
        instructor.setEmail(dto.getPhone());
        instructor.setSpecialization(dto.getSpecialization());
        return instructor;
    }
}
