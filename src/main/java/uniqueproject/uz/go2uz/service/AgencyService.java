package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.repository.AgencyRepository;

@Service
@RequiredArgsConstructor
public class AgencyService {
    private final AgencyRepository agencyRepository;
}
