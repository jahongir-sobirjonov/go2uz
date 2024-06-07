package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.entity.Agency;
import uniqueproject.uz.go2uz.repository.AgencyRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepository agencyRepository;

    public Optional<Agency> getById(UUID id) {
        return agencyRepository.findById(id);
    }
}
