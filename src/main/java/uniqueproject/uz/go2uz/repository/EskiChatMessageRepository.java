package uniqueproject.uz.go2uz.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uniqueproject.uz.go2uz.dto.request.ChatMessage;

import java.util.UUID;

public interface EskiChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
}
