package uniqueproject.uz.go2uz.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewResponse {
    private UUID id;
    private String authorName;
    private String content;

//    private List<ReviewResponse> replies; // Replies as nested reviews
}
