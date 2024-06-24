package uniqueproject.uz.go2uz.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uniqueproject.uz.go2uz.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class ChatMessage extends BaseEntity {
    private String sender;
    private String text;
    private LocalDateTime timestamp;
}

