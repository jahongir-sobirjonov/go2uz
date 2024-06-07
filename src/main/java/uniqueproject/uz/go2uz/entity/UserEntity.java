package uniqueproject.uz.go2uz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import uniqueproject.uz.go2uz.entity.enums.UserRole;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {
    private String name;
    private String surname;
    private String city;
    private String email;
    private String profilePhoto;  // path
    // @Column(unique = true, nullable = false
    private String phoneNumber;
    private String telegramUsername;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType; // tourist or local
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
