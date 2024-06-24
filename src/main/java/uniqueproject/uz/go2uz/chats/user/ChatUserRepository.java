package uniqueproject.uz.go2uz.chats.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatUserRepository  extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
