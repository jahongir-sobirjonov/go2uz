package uniqueproject.uz.go2uz.dto.request;

import lombok.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatRequest {
    private String sender;
    private String text;
//    private LocalDateTime timestamp;
}
