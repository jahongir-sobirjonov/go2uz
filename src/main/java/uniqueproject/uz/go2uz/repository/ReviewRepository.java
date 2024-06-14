package uniqueproject.uz.go2uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uniqueproject.uz.go2uz.entity.Review;
import uniqueproject.uz.go2uz.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByTourId(UUID tourId);

    boolean existsByAuthor(UserEntity author);
}
