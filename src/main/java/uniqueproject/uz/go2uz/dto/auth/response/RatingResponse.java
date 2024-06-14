package uniqueproject.uz.go2uz.dto.auth.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RatingResponse {
    private UUID id;
    private String authorName;
    private Integer rating;

}

