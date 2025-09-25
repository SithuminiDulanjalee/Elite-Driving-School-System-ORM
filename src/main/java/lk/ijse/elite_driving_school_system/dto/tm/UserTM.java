package lk.ijse.elite_driving_school_system.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private String userId;
    private String userName;
    private String password;
    private String role;
}