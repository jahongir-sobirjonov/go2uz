package uniqueproject.uz.go2uz.dto.auth.response;

import lombok.*;

@Getter
@Setter
public class PasswordResetDto {
    private String token;
    private String newPassword;
}