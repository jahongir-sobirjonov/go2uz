package uniqueproject.uz.go2uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uniqueproject.uz.go2uz.repository.TourRepository;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
}
