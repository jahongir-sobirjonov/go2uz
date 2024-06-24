package uniqueproject.uz.go2uz.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uniqueproject.uz.go2uz.entity.UserType;
import uniqueproject.uz.go2uz.entity.enums.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String name;
    private String surname;
    private String city;
    private String email;
    private String profilePhoto;
    private String phoneNumber;
    private String telegramUsername;
    private String password;
    private UserRole role;
}
