package uniqueproject.uz.go2uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uniqueproject.uz.go2uz.entity.Agency;

import java.util.UUID;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, UUID> {
}
