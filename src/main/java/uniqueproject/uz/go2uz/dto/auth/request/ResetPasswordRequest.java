package uniqueproject.uz.go2uz.dto.auth.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}

