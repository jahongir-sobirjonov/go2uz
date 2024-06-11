package uniqueproject.uz.go2uz.dto.auth.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileRequest {
    private String name;
    private String surname;
    private String city;
    private String email;
    private String phoneNumber;
    private String telegramUsername;
    private String profilePhoto;

}
