package uniqueproject.uz.go2uz.dto.auth.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetRequest {
    private String email;
}