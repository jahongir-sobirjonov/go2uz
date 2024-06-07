package uniqueproject.uz.go2uz.dto.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUp {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
}
