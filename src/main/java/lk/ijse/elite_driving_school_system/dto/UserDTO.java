package lk.ijse.elite_driving_school_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;
    private String userName;
    private String password;
    private String role;
}